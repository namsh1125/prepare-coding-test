package site.woorifisa.codingtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woorifisa.codingtest.entity.DailyProblem;
import site.woorifisa.codingtest.entity.Member;
import site.woorifisa.codingtest.entity.Submission;
import site.woorifisa.codingtest.repository.DailyProblemRepository;
import site.woorifisa.codingtest.repository.MemberRepository;
import site.woorifisa.codingtest.repository.SubmissionRepository;
import site.woorifisa.codingtest.util.SlackUtil;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final DailyProblemRepository dailyProblemRepository;

    private final MemberRepository memberRepository;

    private final SubmissionRepository submissionRepository;

    @Value("${slack.webhook-url}")
    private String webhookUrl;

    @Transactional
    public void submitSolution(String slackId, String submissionUrl) {
        Member member = memberRepository.findBySlackId(slackId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        DailyProblem currentProblem = dailyProblemRepository.findCurrentProblem()
                .orElseThrow(() -> new RuntimeException("Current problem not found"));

        if (submissionRepository.existsByMemberAndDailyProblem(member, currentProblem)) {
            throw new IllegalStateException("이미 제출한 문제입니다.");
        }

        Submission submission = Submission.builder()
                .member(member)
                .dailyProblem(currentProblem)
                .submissionUrl(submissionUrl)
                .build();

        submissionRepository.save(submission);

        SlackUtil.sendSubmissionConfirmation(webhookUrl, member, currentProblem.getProblem(), submissionUrl);
    }

}
