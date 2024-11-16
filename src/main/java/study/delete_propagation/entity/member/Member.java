package study.delete_propagation.entity.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.apply.Apply;
import study.delete_propagation.entity.club.clubpost.ClubPost;
import study.delete_propagation.entity.club.clubpost.clubpostcomment.ClubPostComment;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<ClubPost> clubPosts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ClubPostComment> clubPostComments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Apply> applies = new ArrayList<>();

}
