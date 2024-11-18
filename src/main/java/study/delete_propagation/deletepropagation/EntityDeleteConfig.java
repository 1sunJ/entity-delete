package study.delete_propagation.deletepropagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import study.delete_propagation.deletepropagation.v1.EntityDeleteManagerV1;
import study.delete_propagation.deletepropagation.v2.EntityDeleteManagerV2;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EntityDeleteConfig {

    private final Map<String, JpaRepository<?, ?>> repositoryMap;

    @Bean
    public EntityRelationManager entityRelations() {
        return new EntityRelationManagerV1();
    }

    @Bean
    public EntityDeleteManager entityDeleteManager() {
        return new EntityDeleteManagerV2(
                repositoryMap,
                entityRelations());
    }

}
