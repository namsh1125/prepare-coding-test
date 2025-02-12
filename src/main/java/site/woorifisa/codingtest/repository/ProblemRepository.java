package site.woorifisa.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woorifisa.codingtest.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long>, ProblemRepositoryCustom {
}
