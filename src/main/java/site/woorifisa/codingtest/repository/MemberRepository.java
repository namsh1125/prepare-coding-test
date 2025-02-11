package site.woorifisa.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woorifisa.codingtest.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsBySlackId(String slackId);
}
