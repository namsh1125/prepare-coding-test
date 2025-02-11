package site.woorifisa.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woorifisa.codingtest.dto.ProblemDto;
import site.woorifisa.codingtest.service.ProblemService;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/today")
    public ResponseEntity<ProblemDto> getTodayProblem() {
        ProblemDto todayProblem = problemService.getTodayProblem();
        return ResponseEntity.ok(todayProblem);
    }
}
