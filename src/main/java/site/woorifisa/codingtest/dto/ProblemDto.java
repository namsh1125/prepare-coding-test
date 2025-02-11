package site.woorifisa.codingtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import site.woorifisa.codingtest.entity.Category;
import site.woorifisa.codingtest.entity.Platform;
import site.woorifisa.codingtest.entity.Problem;

@Getter
@NoArgsConstructor
public class ProblemDto {
    private Long id;
    private String title;
    private String url;
    private Category category;
    private Platform platform;
    private String difficulty;

    public ProblemDto(Problem problem) {
        this.id = problem.getId();
        this.title = problem.getTitle();
        this.url = problem.getUrl();
        this.category = problem.getCategory();
        this.platform = problem.getPlatform();
        this.difficulty = problem.getDifficulty();
    }
}
