package beyondvision.exercise.dto.response;

import beyondvision.exercise.domain.Exercise;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class ExerciseDetailResponse {

    private final String name;
    private final String description;
    private final Integer caloriesBurned;
    private final Integer difficulty;
    private final Integer categoryNumber;

    public static ExerciseDetailResponse of(final Exercise exercise) {
        return new ExerciseDetailResponse(
                exercise.getName(),
                exercise.getDescription(),
                exercise.getCaloriesBurned(),
                exercise.getDifficulty(),
                exercise.getCategoryNumber());
    }
}
