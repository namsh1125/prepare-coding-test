package site.woorifisa.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woorifisa.codingtest.dto.SlackResponseDto;
import site.woorifisa.codingtest.service.ProblemService;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/today")
    public ResponseEntity<SlackResponseDto> getTodayProblem() {
        try {
            SlackResponseDto response = problemService.getTodayProblem();
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.ok(SlackResponseDto.error("조회에 실패하였습니다: " + e.getMessage()));
        }
    }
}
