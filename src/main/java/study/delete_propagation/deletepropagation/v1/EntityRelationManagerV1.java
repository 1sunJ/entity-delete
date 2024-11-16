package study.delete_propagation.deletepropagation.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import study.delete_propagation.deletepropagation.EntityRelationManager;
import study.delete_propagation.entity.apply.Apply;
import study.delete_propagation.entity.apply.answer.Answer;
import study.delete_propagation.entity.club.Club;
import study.delete_propagation.entity.club.clubpost.ClubPost;
import study.delete_propagation.entity.club.clubpost.clubpostcomment.ClubPostComment;
import study.delete_propagation.entity.member.Member;
import study.delete_propagation.entity.recruitment.Recruitment;
import study.delete_propagation.entity.recruitment.question.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * v1 : 연관관계마다 직접 map 세팅
 */
@Slf4j
//@Component
@RequiredArgsConstructor
public class EntityRelationManagerV1 implements EntityRelationManager {

    private final Map<Class<?>, List<Class<?>>> CHILD_ENTITY_MAP = new HashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void initChildEntityMap() {
        CHILD_ENTITY_MAP.put(Member.class, List.of(ClubPost.class, ClubPostComment.class, Apply.class));
        CHILD_ENTITY_MAP.put(Club.class, List.of(Recruitment.class, ClubPost.class));
        CHILD_ENTITY_MAP.put(Recruitment.class, List.of(Apply.class, Question.class));
        CHILD_ENTITY_MAP.put(Apply.class, List.of(Answer.class));
        CHILD_ENTITY_MAP.put(Question.class, List.of(Answer.class));
        CHILD_ENTITY_MAP.put(ClubPost.class, List.of(ClubPostComment.class));
    }

    @Override
    public List<Class<?>> getChildClasses(Object entity) {
        return CHILD_ENTITY_MAP.get(entity.getClass());
    }

}
