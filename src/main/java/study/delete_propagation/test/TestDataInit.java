package study.delete_propagation.test;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestDataInit {

    @EventListener(ApplicationReadyEvent.class)
    public void testDataInit() {

    }

}
