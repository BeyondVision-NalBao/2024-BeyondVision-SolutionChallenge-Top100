package beyondvision.member.dto.response;

import beyondvision.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class MemberInfoResponse {

    private final String name;
    private final String email;
    private final String socialId;
    private final String profileImageUrl;
    private final Integer age;
    private final String gender;
    private final Integer exerciseGoal;
    private final Integer weight;

    public static MemberInfoResponse of(final Member member) {
        return new MemberInfoResponse(member.getName(), member.getEmail(), member.getSocialId(), member.getProfileImageUrl(), member.getAge(), member.getGender(), member.getExerciseGoal(), member.getWeight());
    }
}
