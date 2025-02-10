package site.woorifisa.codingtest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class DailyProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(nullable = false)
    private LocalDate assignedDate;

    @Builder
    public DailyProblem(Problem problem, LocalDate assignedDate) {
        this.problem = problem;
        this.assignedDate = assignedDate;
    }

}