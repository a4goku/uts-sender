package uts.sender.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @Scheduled(initialDelay = 5000, fixedDelay = 2000)
    public void runTask(){
        System.err.println("执行。。。");
    }
}
