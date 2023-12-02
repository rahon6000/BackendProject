package rahon.home.web.configs;

import java.util.concurrent.Executors;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;

/*
 * Source code from [reference](https://www.baeldung.com/spring-6-virtual-threads)
 * `applicationTaskExecutor` is injected, replacing old one in Spring.
 * `protocolHandlerVirtualThreadExecutorCustomizer` switch Tomcat thread to virtual thread.
 */
@EnableAsync
@Configuration
@ConditionalOnProperty(
  value = "spring.thread-executor",
  havingValue = "virtual"
)
public class ThreadConfig {
    @Bean
    public AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }

    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }
}