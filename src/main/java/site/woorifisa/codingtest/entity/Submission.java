package site.woorifisa.codingtest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_problem_id", nullable = false)
    private DailyProblem dailyProblem;

    @Column(nullable = false)
    private String submissionUrl;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    @Builder
    public Submission(Member member, DailyProblem dailyProblem, String submissionUrl) {
        this.member = member;
        this.dailyProblem = dailyProblem;
        this.submissionUrl = submissionUrl;
        this.submittedAt = LocalDateTime.now();
    }
}
