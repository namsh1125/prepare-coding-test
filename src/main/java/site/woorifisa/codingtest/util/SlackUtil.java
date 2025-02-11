package site.woorifisa.codingtest.util;

import com.slack.api.Slack;
import site.woorifisa.codingtest.entity.Problem;

import java.time.LocalDate;

public class SlackUtil {

    public static void sendDailyProblemNotification(String webhookUrl, Problem problem) {
        try {
            String message = createDailyProblemMessage(problem);
            sendSlackMessage(webhookUrl, message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send Slack notification", e);
        }
    }

    private static String createDailyProblemMessage(Problem problem) {
        return String.format("""
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
    }

    private static void sendSlackMessage(String webhookUrl, String message) throws Exception {
        Slack.getInstance().send(webhookUrl, message);
    }
}
