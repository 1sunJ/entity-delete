package study.delete_propagation.entity.club;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.club.clubpost.ClubPost;
import study.delete_propagation.entity.recruitment.Recruitment;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    @OneToMany(mappedBy = "club")
    private List<Recruitment> recruitments = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<ClubPost> clubPosts = new ArrayList<>();

}
