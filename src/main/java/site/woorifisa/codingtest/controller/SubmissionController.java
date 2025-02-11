package site.woorifisa.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.woorifisa.codingtest.service.SubmissionService;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<String> handleSlackSubmission(
            @RequestParam("user_id") String slackId,
            @RequestParam("text") String submissionUrl) {

        submissionService.submitSolution(slackId, submissionUrl);

        return ResponseEntity.ok("풀이가 성공적으로 제출되었습니다! 👏");
    }
}
