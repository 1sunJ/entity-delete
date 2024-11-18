package study.delete_propagation.entity.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.apply.Apply;
import study.delete_propagation.entity.club.clubpost.ClubPost;
import study.delete_propagation.entity.club.clubpost.clubpostcomment.ClubPostComment;
import study.delete_propagation.logicaldelete.LogicalDeleteEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member implements LogicalDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<ClubPost> clubPosts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ClubPostComment> clubPostComments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Apply> applys = new ArrayList<>();

    private LocalDateTime deletedDateTime;

    @Override
    public void deleteEntity() {
        this.deletedDateTime = LocalDateTime.now();
    }

}
