package site.woorifisa.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woorifisa.codingtest.entity.DailyProblem;
import site.woorifisa.codingtest.entity.Member;
import site.woorifisa.codingtest.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    boolean existsByMemberAndDailyProblem(Member member, DailyProblem dailyProblem);
}
