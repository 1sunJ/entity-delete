package study.delete_propagation.entity.apply;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.apply.answer.Answer;
import study.delete_propagation.entity.member.Member;
import study.delete_propagation.entity.recruitment.Recruitment;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Apply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @OneToMany(mappedBy = "apply")
    private List<Answer> answers = new ArrayList<>();

}
