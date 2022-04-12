package win.minaandyyh.ddnsagent.base.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Scheduled task executor configuration
 *
 * @author masteryyh
 */
@Configuration
public class ThreadPoolConfiguration {
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(Math.max(Runtime.getRuntime().availableProcessors() / 2, 1), threadFactory());
    }

    @Bean
    public ThreadFactory threadFactory() {
        return new ScheduledThreadFactory();
    }

    private static class ScheduledThreadFactory implements ThreadFactory {
        private final AtomicLong counter = new AtomicLong(0L);

        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("scheduled-task-" + counter.incrementAndGet());
            return thread;
        }
    }
}
