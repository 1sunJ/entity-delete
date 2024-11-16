package study.delete_propagation.entity.apply.answer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.apply.Apply;
import study.delete_propagation.entity.recruitment.question.Question;

@Entity
@NoArgsConstructor
@Getter
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private Apply apply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

}
