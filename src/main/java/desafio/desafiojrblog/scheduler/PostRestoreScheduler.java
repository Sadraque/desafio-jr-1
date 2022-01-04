package desafio.desafiojrblog.scheduler;

import desafio.desafiojrblog.service.PostSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PostRestoreScheduler {

    @Autowired
    private PostSchedulerService postSchedulerService;

    @Scheduled(cron = "${post.deleteOld.cronTimer}")
    public void deleteOldPosts() {
        postSchedulerService.deleteOldPosts();
    }

}
