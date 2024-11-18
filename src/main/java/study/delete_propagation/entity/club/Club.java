package study.delete_propagation.entity.club;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.club.clubpost.ClubPost;
import study.delete_propagation.entity.recruitment.Recruitment;
import study.delete_propagation.logicaldelete.LogicalDeleteEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Club implements LogicalDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    @OneToMany(mappedBy = "club")
    private List<Recruitment> recruitments = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<ClubPost> clubPosts = new ArrayList<>();

    private LocalDateTime deletedDateTime;

    @Override
    public void deleteEntity() {
        this.deletedDateTime = LocalDateTime.now();
    }

}
