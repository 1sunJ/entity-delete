package study.delete_propagation.entity.club.clubpost;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.delete_propagation.entity.club.Club;
import study.delete_propagation.entity.club.clubpost.clubpostcomment.ClubPostComment;
import study.delete_propagation.entity.member.Member;
import study.delete_propagation.logicaldelete.LogicalDeleteEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class ClubPost implements LogicalDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "clubPost")
    private List<ClubPostComment> clubPostComments = new ArrayList<>();

    private LocalDateTime deletedDateTime;

    public ClubPost(Member member, Club club) {
        this.member = member;
        this.club = club;
    }

    @Override
    public void deleteEntity() {
        this.deletedDateTime = LocalDateTime.now();
    }

}
