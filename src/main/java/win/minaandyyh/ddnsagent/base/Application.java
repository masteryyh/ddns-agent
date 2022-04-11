package win.minaandyyh.ddnsagent.base;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author 22454
 */
@Slf4j
public class Application {
    private static final String BANNER_FILE_NAME = "banner.txt";

    public static void run() {
        Application application = new Application();
        application.start();
    }

    public void start() {
        try (AnnotationConfigApplicationContext applicationContext =
                     new AnnotationConfigApplicationContext(Application.class)) {
            init();
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        banner();
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
