package site.woorifisa.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.woorifisa.codingtest.dto.SlackResponseDto;
import site.woorifisa.codingtest.exception.MemberAlreadyExistsException;
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
    public ResponseEntity<SlackResponseDto> registerMember(@RequestParam("user_id") String slackId, @RequestParam("user_name") String userName) {
        try {
            memberService.registerMember(slackId, userName);
            return ResponseEntity.ok(SlackResponseDto.success(String.format("%s님 등록이 완료되었습니다!", userName)));

        } catch (MemberAlreadyExistsException e) {
            return ResponseEntity.ok(SlackResponseDto.error("이미 등록된 사용자입니다."));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(SlackResponseDto.error(String.format("등록에 실패하였습니다: %s", e.getMessage())));
        }
    }
}
