package site.woorifisa.codingtest.service;

import com.slack.api.Slack;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woorifisa.codingtest.entity.DailyProblem;
import site.woorifisa.codingtest.entity.Problem;
import site.woorifisa.codingtest.repository.DailyProblemRepository;
import site.woorifisa.codingtest.repository.ProblemRepository;

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

        sendSlackNotification(problem);
    }

    private void sendSlackNotification(Problem problem) {
        String blockKit = String.format("""
                        {
                            "blocks": [
                                {
                                    "type": "header",
                                    "text": {
                                        "type": "plain_text",
                                        "text": "🎯 오늘의 코딩 문제 (%s)",
                                        "emoji": true
                                    }
                                },
                                {
                                    "type": "divider"
                                },
                                {
                                    "type": "section",
                                    "fields": [
                                        {
                                            "type": "mrkdwn",
                                            "text": "*제목:*\\n%s"
                                        },
                                        {
                                            "type": "mrkdwn",
                                            "text": "*카테고리:*\\n%s"
                                        }
                                    ]
                                },
                                {
                                    "type": "section",
                                    "fields": [
                                        {
                                            "type": "mrkdwn",
                                            "text": "*난이도:*\\n%s"
                                        },
                                        {
                                            "type": "mrkdwn",
                                            "text": "*플랫폼:*\\n%s"
                                        }
                                    ]
                                },
                                {
                                    "type": "section",
                                    "text": {
                                        "type": "mrkdwn",
                                        "text": "✨ *문제 풀러가기*"
                                    },
                                    "accessory": {
                                        "type": "button",
                                        "text": {
                                            "type": "plain_text",
                                            "text": "문제 보기",
                                            "emoji": true
                                        },
                                        "url": "%s",
                                        "style": "primary"
                                    }
                                }
                            ]
                        }""",
                LocalDate.now(),
                problem.getTitle(),
                problem.getCategory().getName(),
                problem.getDifficulty(),
                problem.getPlatform(),
                problem.getUrl()
        );

        try {
            Slack.getInstance().send(webhookUrl, blockKit);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send Slack notification", e);
        }
    }
}
