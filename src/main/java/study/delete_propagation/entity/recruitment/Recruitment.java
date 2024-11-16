package study.delete_propagation.entity.recruitment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.apply.Apply;
import study.delete_propagation.entity.club.Club;
import study.delete_propagation.entity.recruitment.question.Question;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Recruitment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "recruitment")
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment")
    private List<Question> questions = new ArrayList<>();

}
