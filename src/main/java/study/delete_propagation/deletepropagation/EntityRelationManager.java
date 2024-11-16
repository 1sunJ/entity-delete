package study.delete_propagation.deletepropagation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface EntityRelationManager {

    List<Object> getChildEntities(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
