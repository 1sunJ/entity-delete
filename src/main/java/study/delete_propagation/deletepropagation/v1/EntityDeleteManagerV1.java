package study.delete_propagation.deletepropagation.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import study.delete_propagation.deletepropagation.EntityDeleteManager;
import study.delete_propagation.deletepropagation.EntityRelationManager;
import study.delete_propagation.entity.club.deletedclub.DeletedClub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * v1 : 재귀로 자식 탐색하며 물리 삭제
 */
@Slf4j
//@Component
@Transactional
@RequiredArgsConstructor
public class EntityDeleteManagerV1 implements EntityDeleteManager {

    private final Map<String, JpaRepository<?, ?>> repositoryMap;
    private final EntityRelationManager entityRelationManager;

    @Override
    public void deleteEntity(Object entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // 자식 엔티티들 삭제
        List<Object> childEntities = entityRelationManager.getChildEntities(entity);
        for (Object childEntity : childEntities) {
            deleteEntity(childEntity);
        }

        // 엔티티 삭제
        Class<?> clazz = entity.getClass();
        String key = resolveMapKey(clazz);
        JpaRepository<?, ?> jpaRepository = repositoryMap.get(key);

        Method deleteMethod = jpaRepository.getClass().getMethod("delete", Object.class);
        deleteMethod.invoke(jpaRepository, entity);
    }

    private String resolveMapKey(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        String entityName = String.valueOf(simpleName.charAt(0)).toLowerCase() + simpleName.substring(1);
        return entityName + "Repository";
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info(" ===== start ===== ");
        for (String key : repositoryMap.keySet()) {
            log.info("key : {}, value : {}", key, repositoryMap.get(key));
        }
        log.info(" ====== end ====== ");

        String key = resolveMapKey(DeletedClub.class);
        log.info("resolved key : {}", key);
    }

}
