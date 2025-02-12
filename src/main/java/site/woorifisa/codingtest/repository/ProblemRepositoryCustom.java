package site.woorifisa.codingtest.repository;

import site.woorifisa.codingtest.entity.Problem;

import java.util.Optional;

public interface ProblemRepositoryCustom {
    Optional<Problem> findRandomUnsolvedProblem();
}