package site.woorifisa.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.woorifisa.codingtest.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 사용자를 등록하는 API
     *
     * @param slackId  사용자의 슬랙 ID
     * @param userName 사용자의 이름
     * @return 등록 결과 여부 메시지
     * @see <a href="https://api.slack.com/interactivity/slash-commands">Slash Commands</a>
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestParam("user_id") String slackId, @RequestParam("user_name") String userName) {
        try {
            memberService.registerMember(slackId, userName);
            return ResponseEntity.ok(String.format("✅ %s님 등록이 완료되었습니다!", userName));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(String.format("❌ 등록 실패: %s", e.getMessage()));
        }
    }
}
