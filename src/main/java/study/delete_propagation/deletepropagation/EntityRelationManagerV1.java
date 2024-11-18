package study.delete_propagation.deletepropagation;

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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * v1 : 연관관계 class 를 map 으로 지정 후 getEntity 메서드를 리플렉션으로 동적 호출
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
    public List<Object> getChildEntities(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Object> childEntities = new ArrayList<>();

        List<Class<?>> childClasses = getChildClasses(entity);
        for (Class<?> childClass : childClasses) {
            Method method = entity.getClass().getMethod("get" + childClass.getSimpleName() + "s");
            List<?> result = (List<?>) method.invoke(entity);

            for (Object o : result) {
                log.info("child : {}", o);
            }

            childEntities.addAll(result);
        }

        return childEntities;
    }

    public List<Class<?>> getChildClasses(Object entity) {
        if (CHILD_ENTITY_MAP.containsKey(entity.getClass())) {
            return CHILD_ENTITY_MAP.get(entity.getClass());
        } else {
            return List.of();
        }
    }

}
