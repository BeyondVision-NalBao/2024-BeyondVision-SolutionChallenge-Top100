package beyondvision.member.domain;

import beyondvision.global.BaseEntity;
import beyondvision.record.domain.Record;
import beyondvision.routine.domain.Routine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String socialId;

    @Column(nullable = false)
    private String profileImageUrl;

    private Integer age;

    private String gender;

    private Integer exerciseGoal;

    private Integer weight;

    @OneToMany(mappedBy = "member")
    private List<Routine> routines = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Record> records = new ArrayList<>();

    @Builder
    public Member(
            final String name,
            final String email,
            final String socialId,
            final String profileImageUrl,
            final Integer age,
            final String gender,
            final Integer exerciseGoal,
            final Integer weight

    ) {
        this.name = name;
        this.email = email;
        this.socialId = socialId;
        this.profileImageUrl = profileImageUrl;
        this.age = age;
        this.gender = gender;
        this.exerciseGoal = exerciseGoal;
        this.weight = weight;
    }

    public void updateMember(final Integer exerciseGoal) {
        this.exerciseGoal = exerciseGoal;
    }
}
