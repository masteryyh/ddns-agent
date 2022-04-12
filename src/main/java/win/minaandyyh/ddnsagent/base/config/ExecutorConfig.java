package win.minaandyyh.ddnsagent.base.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author 22454
 */
@Slf4j
@EnableScheduling
@Configuration
public class ExecutorConfig {
    private static final int AVAILABLE_PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    @Bean
    public TaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        executor.setCorePoolSize(Math.max(AVAILABLE_PROCESSORS_COUNT / 4, 1));
        executor.setMaxPoolSize(Math.max(AVAILABLE_PROCESSORS_COUNT / 2, 1));
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("async-service-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        log.info("thread pool configuration completed.");
        return executor;
    }

    private static final class VisibleThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
        private void showThreadPoolInfo(String prefix) {
            ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
            log.info("{}{},taskCount [{}],completedTaskCount [{}],activeCount [{}],queueSize [{}]",
                    this.getThreadNamePrefix(),
                    prefix,
                    threadPoolExecutor.getTaskCount(),
                    threadPoolExecutor.getCompletedTaskCount(),
                    threadPoolExecutor.getActiveCount(),
                    threadPoolExecutor.getQueue().size());
        }

        @Override
        public void execute(@NotNull Runnable task) {
            showThreadPoolInfo("1. do execute");
            super.execute(task);
        }

        @NotNull
        @Override
        public Future<?> submit(@NotNull Runnable task) {
            showThreadPoolInfo("1. do submit");
            return super.submit(task);
        }

        @NotNull
        @Override
        public <T> Future<T> submit(@NotNull Callable<T> task) {
            showThreadPoolInfo("2. do submit");
            return super.submit(task);
        }

        @NotNull
        @Override
        public ListenableFuture<?> submitListenable(@NotNull Runnable task) {
            showThreadPoolInfo("1. do submitListenable");
            return super.submitListenable(task);
        }

        @NotNull
        @Override
        public <T> ListenableFuture<T> submitListenable(@NotNull Callable<T> task) {
            showThreadPoolInfo("2. do submitListenable");
            return super.submitListenable(task);
        }
    }
}
