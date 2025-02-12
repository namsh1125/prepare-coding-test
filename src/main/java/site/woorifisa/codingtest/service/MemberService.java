package site.woorifisa.codingtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woorifisa.codingtest.entity.Member;
import site.woorifisa.codingtest.exception.MemberAlreadyExistsException;
import site.woorifisa.codingtest.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void registerMember(String slackId, String name) {
        if (memberRepository.existsBySlackId(slackId)) {
            throw new MemberAlreadyExistsException("이미 등록된 사용자입니다.");
        }

        Member member = Member.builder()
                .slackId(slackId)
                .name(name)
                .build();

        memberRepository.save(member);
    }

}
