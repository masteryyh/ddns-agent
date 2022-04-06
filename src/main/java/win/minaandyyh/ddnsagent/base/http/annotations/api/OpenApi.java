package win.minaandyyh.ddnsagent.base.http.annotations.api;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author 22454
 */
@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenApi {

    String url() default "";

    Class<?> fallback() default Object.class;
}
