package beyondvision.record.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRecordRequest {

    private Integer exerciseTime;
    private Integer exerciseCount;
    private Integer successCount;
    private Double caloriesBurnedSum;
    private Integer averageHeartRate;

    @NotNull(message = "회원 id는 필수입니다.")
    private Long memberId;
}
