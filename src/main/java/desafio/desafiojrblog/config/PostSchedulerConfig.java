package desafio.desafiojrblog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PostSchedulerConfig {

    @Value("${post.deleteOld.maxPeriodInDays}")
    private Long postRestoreMaxPeriod;

    @Value("${post.deleteOld.cronTimer}")
    private String postRestoreTimer;
}
