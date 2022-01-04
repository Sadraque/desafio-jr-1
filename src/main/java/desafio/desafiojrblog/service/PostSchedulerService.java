package desafio.desafiojrblog.service;

import desafio.desafiojrblog.config.PostSchedulerConfig;
import desafio.desafiojrblog.logging.Logger;
import static desafio.desafiojrblog.logging.LoggerBasicKeyEnum.*;
import desafio.desafiojrblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

@Service
public class PostSchedulerService {

    @Autowired
    private PostSchedulerConfig postSchedulerConfig;

    @Autowired
    private PostRepository postRepository;

    @Async
    public void deleteOldPosts() {
        Logger log = Logger.builder();

        StopWatch watch = new StopWatch();
        watch.start();

        try {
            log.add(MESSAGE, "running schedule")
                    .add(SCHEDULE, "deleteOldPosts");

            Long maxPeriod = postSchedulerConfig.getPostRestoreMaxPeriod();
            log.add("maxPeriod", maxPeriod);

            if (maxPeriod == null) {
                return;
            }

            LocalDateTime referenceDate = LocalDateTime.now().minusDays(maxPeriod);
            log.add(DATE, referenceDate.toString());

            postRepository.deleteOldPosts(referenceDate);

        } catch (Exception ex) {
            log.add(EXCEPTION, ex.getMessage())
                    .servere();

            return;
        }

        watch.stop();

        log.add(DURATION_TIME, watch.getTotalTimeMillis())
                .info();
    }
}
