package site.woorifisa.codingtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woorifisa.codingtest.entity.DailyProblem;
import site.woorifisa.codingtest.entity.Problem;
import site.woorifisa.codingtest.repository.DailyProblemRepository;
import site.woorifisa.codingtest.repository.ProblemRepository;
import site.woorifisa.codingtest.util.SlackUtil;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    private final DailyProblemRepository dailyProblemRepository;

    @Value("${slack.webhook-url}")
    private String webhookUrl;

    @Scheduled(cron = "0 0 9 * * *") // 매일 오전 9시에 실행
    @Transactional
    public void assignAndNotifyDailyProblem() {
        Problem problem = problemRepository.findRandomUnsolvedProblem()
                .orElseThrow(() -> new RuntimeException("No unsolved problems available"));

        DailyProblem dailyProblem = DailyProblem.builder()
                .problem(problem)
                .assignedDate(LocalDate.now())
                .build();

        dailyProblemRepository.save(dailyProblem);

        SlackUtil.sendDailyProblemNotification(webhookUrl, problem);
    }

}
