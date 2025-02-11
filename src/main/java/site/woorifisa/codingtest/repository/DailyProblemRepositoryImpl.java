package site.woorifisa.codingtest.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import site.woorifisa.codingtest.entity.DailyProblem;
import site.woorifisa.codingtest.entity.QDailyProblem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@RequiredArgsConstructor
public class DailyProblemRepositoryImpl implements DailyProblemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<DailyProblem> findCurrentProblem() {
        QDailyProblem dailyProblem = QDailyProblem.dailyProblem;
        LocalDate targetDate = LocalTime.now().isBefore(LocalTime.of(9, 0)) 
            ? LocalDate.now().minusDays(1) 
            : LocalDate.now();

        return Optional.ofNullable(
            queryFactory
                .selectFrom(dailyProblem)
                .where(dailyProblem.assignedDate.eq(targetDate))
                .fetchOne()
        );
    }
}
