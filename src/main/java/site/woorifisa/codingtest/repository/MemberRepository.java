package site.woorifisa.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woorifisa.codingtest.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsBySlackId(String slackId);

    Optional<Member> findBySlackId(String slackId);
}
