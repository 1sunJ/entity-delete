package study.delete_propagation.logicaldelete;

import java.time.LocalDateTime;

public interface LogicalDeleteEntity {

    public LocalDateTime getDeletedDateTime();

    public void deleteEntity();

}
