package study.delete_propagation.deletepropagation;

import java.lang.reflect.InvocationTargetException;

public interface EntityDeleteManager {

    void deleteEntity(Object entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

}
