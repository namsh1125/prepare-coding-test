package site.woorifisa.codingtest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slackId;

    @Column(nullable = false)
    private String name;

    @Builder
    public Member(String slackId, String name) {
        this.slackId = slackId;
        this.name = name;
    }
}
