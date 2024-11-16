package study.delete_propagation.test;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.delete_propagation.entity.apply.Apply;
import study.delete_propagation.entity.apply.ApplyRepository;
import study.delete_propagation.entity.apply.answer.AnswerRepository;
import study.delete_propagation.entity.club.Club;
import study.delete_propagation.entity.club.ClubRepository;
import study.delete_propagation.entity.club.clubpost.ClubPost;
import study.delete_propagation.entity.club.clubpost.ClubPostRepository;
import study.delete_propagation.entity.club.clubpost.clubpostcomment.ClubPostComment;
import study.delete_propagation.entity.club.clubpost.clubpostcomment.ClubPostCommentRepository;
import study.delete_propagation.entity.member.Member;
import study.delete_propagation.entity.member.MemberRepository;
import study.delete_propagation.entity.recruitment.Recruitment;
import study.delete_propagation.entity.recruitment.RecruitmentRepository;
import study.delete_propagation.entity.recruitment.question.QuestionRepository;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final ClubPostRepository clubPostRepository;
    private final ClubPostCommentRepository clubPostCommentRepository;
    private final ApplyRepository applyRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void testDataInit() {
        Member member1 = new Member();
        memberRepository.save(member1);

        Club club1 = new Club();
        Club club2 = new Club();
        Club club3 = new Club();
        clubRepository.saveAll(List.of(club1, club2, club3));

        ClubPost clubPost1 = new ClubPost(member1, club1);
        ClubPost clubPost2 = new ClubPost(member1, club1);
        ClubPost clubPost3 = new ClubPost(member1, club1);
        ClubPost clubPost4 = new ClubPost(member1, club2);
        ClubPost clubPost5 = new ClubPost(member1, club2);
        ClubPost clubPost6 = new ClubPost(member1, club2);
        ClubPost clubPost7 = new ClubPost(member1, club3);
        ClubPost clubPost8 = new ClubPost(member1, club3);
        ClubPost clubPost9 = new ClubPost(member1, club3);
        clubPostRepository.saveAll(List.of(clubPost1, clubPost2, clubPost3, clubPost4, clubPost5, clubPost6, clubPost7, clubPost8, clubPost9));

        ClubPostComment clubPostComment1 = new ClubPostComment(member1, clubPost1);
        ClubPostComment clubPostComment2 = new ClubPostComment(member1, clubPost1);
        ClubPostComment clubPostComment3 = new ClubPostComment(member1, clubPost1);
        ClubPostComment clubPostComment4 = new ClubPostComment(member1, clubPost2);
        ClubPostComment clubPostComment5 = new ClubPostComment(member1, clubPost2);
        ClubPostComment clubPostComment6 = new ClubPostComment(member1, clubPost2);
        clubPostCommentRepository.saveAll(List.of(clubPostComment1, clubPostComment2, clubPostComment3, clubPostComment4, clubPostComment5, clubPostComment6));

        Recruitment recruitment1 = new Recruitment(club1);
        Recruitment recruitment2 = new Recruitment(club1);
        Recruitment recruitment3 = new Recruitment(club2);
        Recruitment recruitment4 = new Recruitment(club2);
        Recruitment recruitment5 = new Recruitment(club3);
        recruitmentRepository.saveAll(List.of(recruitment1, recruitment2, recruitment3, recruitment4, recruitment5));

        Apply apply1 = new Apply(member1, recruitment1);
        Apply apply2 = new Apply(member1, recruitment1);
        Apply apply3 = new Apply(member1, recruitment2);
        Apply apply4 = new Apply(member1, recruitment2);
        Apply apply5 = new Apply(member1, recruitment3);
        Apply apply6 = new Apply(member1, recruitment3);
        applyRepository.saveAll(List.of(apply1, apply2, apply3, apply4, apply5, apply6));

    }

}
