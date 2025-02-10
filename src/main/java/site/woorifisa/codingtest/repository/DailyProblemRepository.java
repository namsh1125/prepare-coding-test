package site.woorifisa.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woorifisa.codingtest.entity.DailyProblem;

public interface DailyProblemRepository extends JpaRepository<DailyProblem, Long> {
}
