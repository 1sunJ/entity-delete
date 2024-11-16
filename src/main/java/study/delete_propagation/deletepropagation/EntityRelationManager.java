package study.delete_propagation.deletepropagation;

import java.util.List;

public interface EntityRelationManager {

    List<Class<?>> getChildClasses(Object entity);

}
