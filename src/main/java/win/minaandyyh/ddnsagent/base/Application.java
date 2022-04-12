package win.minaandyyh.ddnsagent.base;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ResourceUtils;
import win.minaandyyh.ddnsagent.base.errors.ApplicationException;
import win.minaandyyh.ddnsagent.base.http.services.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author 22454
 */
@Slf4j
public record Application(Class<?> mainClass) {
    private static final String BANNER_FILE_NAME = "banner.txt";

    public static void run() {
        Class<?> mainClass = findMainClass();
        Application application = new Application(mainClass);
        application.start();
    }

    private static Class<?> findMainClass() {
        StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();
        int length = stackTraceArray.length;
        String className = stackTraceArray[length - 1].getClassName();
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw ApplicationException.of("Can not found class <%s>".formatted(className));
        }
    }

    public void start() {
        banner();
        try (AnnotationConfigApplicationContext applicationContext =
                     new AnnotationConfigApplicationContext(mainClass)) {
            init();
            applicationContext.getBean(Test.class);
            while (true) {

            }
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        log.info("DDNS Application start.");
    }

    private void banner() {
        try {
            File bannerFile = ResourceUtils.getFile("classpath:%s".formatted(BANNER_FILE_NAME));
            String banner = FileUtil.readString(bannerFile, StandardCharsets.UTF_8);
            log.info(banner);
        } catch (Exception e) {
            log.warn("failed to read banner.");
        }
    }

}
