package site.woorifisa.codingtest.repository;

import site.woorifisa.codingtest.entity.DailyProblem;

import java.util.Optional;

public interface DailyProblemRepositoryCustom {
    Optional<DailyProblem> findCurrentProblem();
}
