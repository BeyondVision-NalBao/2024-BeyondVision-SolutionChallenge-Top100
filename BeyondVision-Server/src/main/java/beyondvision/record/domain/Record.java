package beyondvision.record.domain;

import beyondvision.exercise.domain.Exercise;
import beyondvision.global.BaseEntity;
import beyondvision.member.domain.Member;
import beyondvision.routine.domain.Routine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "record")
@Getter
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Record extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Integer exerciseTime;

    private Double caloriesBurnedSum;

    private Integer averageHeartRate;

    private Integer exerciseCount;
    private Integer successCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Builder
    public Record(
            final Integer exerciseTime,
            final Double caloriesBurnedSum,
            final Integer averageHeartRate,
            final Integer exerciseCount,
            final Member member,
            final Exercise exercise,
            final Integer successCount
    ) {
        this.exerciseTime = exerciseTime;
        this.caloriesBurnedSum = caloriesBurnedSum;
        this.averageHeartRate = averageHeartRate;
        this.exerciseCount = exerciseCount;
        this.member = member;
        this.exercise = exercise;
        this.successCount = successCount;
    }
}
