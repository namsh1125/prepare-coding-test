package site.woorifisa.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woorifisa.codingtest.dto.SlackResponseDto;
import site.woorifisa.codingtest.entity.Problem;
import site.woorifisa.codingtest.service.ProblemService;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/today")
    public ResponseEntity<SlackResponseDto> getTodayProblem() {
        try {
            Problem problem = problemService.getTodayProblem();
            String message = String.format("오늘 해결해야 하는 문제는 [%s](%s)입니다.", problem.getTitle(), problem.getUrl());

            return ResponseEntity.ok(SlackResponseDto.success(message));
        } catch (Exception e) {
            return ResponseEntity.ok(SlackResponseDto.error("조회에 실패하였습니다: " + e.getMessage()));
        }
    }
}
