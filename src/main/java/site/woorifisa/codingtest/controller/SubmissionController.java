package site.woorifisa.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.woorifisa.codingtest.dto.SlackResponseDto;
import site.woorifisa.codingtest.exception.*;
import site.woorifisa.codingtest.service.SubmissionService;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SlackResponseDto> handleSlackSubmission(
            @RequestParam("user_id") String slackId,
            @RequestParam("text") String submissionUrl) {
        try {
            if (submissionUrl == null || submissionUrl.trim().isEmpty()) {
                throw new MissingSubmissionUrlException("제출 URL이 없습니다. 문제 풀이 URL을 입력해주세요.");
            }

            submissionService.submitSolution(slackId, submissionUrl);
            return ResponseEntity.ok(SlackResponseDto.success("문제 풀이가 성공적으로 제출되었습니다!"));

        } catch (UnregisteredMemberException | MissingSubmissionUrlException | NoDailyProblemException | DuplicateSubmissionException e) {
            return ResponseEntity.ok(SlackResponseDto.error(e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.ok(SlackResponseDto.error("제출에 실패하였습니다: " + e.getMessage()));
        }
    }
}
