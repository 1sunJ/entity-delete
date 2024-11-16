package study.delete_propagation.entity.recruitment.deletedrecruitment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.club.deletedclub.DeletedClub;

@Entity
@NoArgsConstructor
@Getter
public class DeletedRecruitment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deleted_recruitment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_club_id")
    private DeletedClub deletedClub;

}
