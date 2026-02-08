package academy.prog.bot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class BotKeepAlive {

    @Bean
    public ApplicationRunner keepAliveRunner() {
        return args -> new CountDownLatch(1).await();
    }
}