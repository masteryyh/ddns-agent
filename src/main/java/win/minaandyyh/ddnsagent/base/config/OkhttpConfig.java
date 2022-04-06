package win.minaandyyh.ddnsagent.base.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 22454
 */
@Configuration
public class OkhttpConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        System.out.println("ok http client");
        return new OkHttpClient();
    }
}
