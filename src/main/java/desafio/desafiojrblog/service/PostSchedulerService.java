package desafio.desafiojrblog.service;

import desafio.desafiojrblog.config.PostSchedulerConfig;
import desafio.desafiojrblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostSchedulerService {

    @Autowired
    private PostSchedulerConfig postSchedulerConfig;

    @Autowired
    private PostRepository postRepository;

    @Async
    public void deleteOldPosts() {
        try {
            Long maxPeriod = postSchedulerConfig.getPostRestoreMaxPeriod();

            if(maxPeriod == null) {
                return;
            }

            LocalDateTime referenceDate = LocalDateTime.now().minusDays(maxPeriod);

            postRepository.deleteOldPosts(referenceDate);

        } catch (Exception ex) {
            return;
        }
    }
}
