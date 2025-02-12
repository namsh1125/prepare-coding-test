package site.woorifisa.codingtest.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import site.woorifisa.codingtest.entity.Problem;
import site.woorifisa.codingtest.entity.QProblem;
import site.woorifisa.codingtest.entity.QDailyProblem;

import java.util.Optional;

@RequiredArgsConstructor
public class ProblemRepositoryImpl implements ProblemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Problem> findRandomUnsolvedProblem() {
        QProblem problem = QProblem.problem;
        QDailyProblem dailyProblem = QDailyProblem.dailyProblem;

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(problem)
                        .where(problem.id.notIn(
                                queryFactory
                                        .select(dailyProblem.problem.id)
                                        .from(dailyProblem)
                        ))
                        .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
                        .limit(1)
                        .fetchOne()
        );
    }
}
