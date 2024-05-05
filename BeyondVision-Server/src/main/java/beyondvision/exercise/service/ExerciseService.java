package beyondvision.exercise.service;

import beyondvision.exercise.domain.Exercise;
import beyondvision.exercise.domain.repository.ExerciseRepository;
import beyondvision.exercise.dto.response.ExerciseDetailResponse;
import beyondvision.global.exeption.BadRequestException;
import beyondvision.record.domain.Record;
import beyondvision.record.domain.repository.RecordRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLType;
import java.util.*;

import static beyondvision.global.exeption.ExceptionCode.INVALID_EXERCISE_CATEGORY;

@Service
@RequiredArgsConstructor
@Transactional
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final RecordRepository recordRepository;

    @Transactional(readOnly = true)
    public List<ExerciseDetailResponse> getExerciseDetailByCategory(final Integer categoryNumber) {
        final List<Exercise> exercises = exerciseRepository.findByCategoryNumber(categoryNumber);

        if (exercises.isEmpty())
            throw new BadRequestException(INVALID_EXERCISE_CATEGORY);

        return exercises.stream()
                .map(ExerciseDetailResponse::of)
                .toList();
    }

    @Transactional
    public ExerciseDetailResponse getRandomExercise(){
        Random random = new Random();
        long randomNumber = random.nextInt(11) + 1;
        Exercise randomExercise = exerciseRepository.findExerciseById(randomNumber);
        return ExerciseDetailResponse.of(randomExercise);
    }

    //랜덤으로 운동 찾기
    @Transactional
    public Exercise getRandomRecommandExercise(){
        Random random = new Random();
        long randomNumner = random.nextInt(11)+1;
        System.out.println("랜덤");
        return exerciseRepository.findExerciseById(randomNumner);
    }

    //안 한 운동 찾기
    @Transactional
    public Exercise findMissingExercise(List<Record> records){
        List<Long> recordExerciseIds = new ArrayList<>();
        for (Record record : records) {
            recordExerciseIds.add(record.getExercise().getId());
        }
        Set<Long> missingIds = new HashSet<>();
        for (Long i = 1L; i<=11; i++){
            if(!recordExerciseIds.contains(i))
                missingIds.add(i);
        }
        Random random = new Random();
        if (missingIds.size() > 0) {
            Long randomIndex = random.nextLong(missingIds.size());
            long[] missingArray = missingIds.stream().mapToLong(Long::longValue).toArray();
            Long targetExerciseId = missingArray[Math.toIntExact(randomIndex)];
            return exerciseRepository.findExerciseById(targetExerciseId);
        }
        else
            return null;
    }

    //적게 한 운동 찾기
    @Transactional
    public Exercise findMinExercise(List<Record> records){
        int minCount = Integer.MAX_VALUE;
        Record minRecord = null;
        for (Record record : records) {
            if (record.getSuccessCount() < minCount) {
                minRecord = record;
                minCount = record.getSuccessCount();
            }
        }
        return exerciseRepository.findExerciseById(minRecord.getExercise().getId());

    }

    //운동 추천
    @Transactional
    public ExerciseDetailResponse getRecommandExercise(final Long memberId) {
        List<Record> records = recordRepository.findRecordsByMemberId(memberId);
        Exercise recommandExercise = findMissingExercise(records);
        if (recommandExercise != null)
            return ExerciseDetailResponse.of(recommandExercise);
        recommandExercise = findMinExercise(records);
        if (recommandExercise != null)
            return ExerciseDetailResponse.of(recommandExercise);
        else
            return ExerciseDetailResponse.of(getRandomRecommandExercise());
    }
}
