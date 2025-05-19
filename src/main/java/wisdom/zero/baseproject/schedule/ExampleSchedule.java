package wisdom.zero.baseproject.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExampleSchedule {

    private static final long MILLIS_IN_A_DAY = 1000L * 60 * 60 * 24;
    @Scheduled(cron = "0 */1 * * * *", zone = "Europe/Madrid")
    public void run() {
        log.info("EXECUTING CRON JOB");
    }

}
