package site.woorifisa.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woorifisa.codingtest.entity.Problem;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    
    @Query("SELECT p FROM Problem p WHERE p.id NOT IN (SELECT dp.problem.id FROM DailyProblem dp) ORDER BY RAND() LIMIT 1")
    Optional<Problem> findRandomUnsolvedProblem();
}
