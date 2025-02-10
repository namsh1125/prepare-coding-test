package site.woorifisa.codingtest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    // 문제 출처
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Platform platform;

    private int difficulty;

    public String getDifficulty() {
        return switch (platform) {
            case BOJ -> {
                if (difficulty >= 1 && difficulty <= 5) yield "브론즈 " + (6 - difficulty);
                else if (difficulty >= 6 && difficulty <= 10) yield "실버 " + (11 - difficulty);
                else if (difficulty >= 11 && difficulty <= 15) yield "골드 " + (16 - difficulty);
                else if (difficulty >= 16 && difficulty <= 20) yield "플래티넘 " + (21 - difficulty);
                else if (difficulty >= 21 && difficulty <= 25) yield "다이아몬드 " + (26 - difficulty);
                else if (difficulty >= 26 && difficulty <= 30) yield "루비 " + (31 - difficulty);
                else yield "Unknown";
            }
            case PROGRAMMERS -> difficulty + "단계";
            case SWEA -> "Level " + difficulty;
        };
    }

}
