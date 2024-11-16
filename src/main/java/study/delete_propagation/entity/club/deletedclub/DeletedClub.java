package study.delete_propagation.entity.club.deletedclub;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class DeletedClub {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deleted_club_id")
    private Long id;



}
