package org.acme;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

@ApplicationScoped
public class CounterBean {

    private AtomicInteger counter = new AtomicInteger();

    public int get() {
        return counter.get();
    }

//    @Scheduled(every="10s")
//    void increment() {
//        counter.incrementAndGet();
//        System.out.println(counter.get());
//    }
//

    @Scheduled(every="10s")
    //@Scheduled(cron="* * * * * ?")
    void cronJob(ScheduledExecution execution) {
        counter.incrementAndGet();
        System.out.println(execution.getScheduledFireTime());
    }

//    @Scheduled(cron = "{cron.expr}")
//    void cronJobWithExpressionInConfig() {
//        counter.incrementAndGet();
//        System.out.println("Cron expression configured in application.properties");
//
}

