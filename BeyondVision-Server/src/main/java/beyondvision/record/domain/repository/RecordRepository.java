package beyondvision.record.domain.repository;

import beyondvision.member.domain.Member;
import beyondvision.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = """
            SELECT *
            FROM record record
            WHERE record.member_id = :memberId AND record.created_time >= DATE_ADD(NOW(), INTERVAL - 3 MONTH)
            ORDER BY record.created_time DESC
            """, nativeQuery = true)
    List<Record> getRecordByMemberIdBetween(@Param("memberId") final Long memberId);

    void deleteAllByMember(Member member);
    List<Record> findRecordsByMemberId(final Long memberId);

}
