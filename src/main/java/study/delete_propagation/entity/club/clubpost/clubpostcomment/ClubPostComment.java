package study.delete_propagation.entity.club.clubpost.clubpostcomment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.club.clubpost.ClubPost;
import study.delete_propagation.entity.member.Member;

@Entity
@NoArgsConstructor
@Getter
public class ClubPostComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_post_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_post_id")
    private ClubPost clubPost;

}
