package win.minaandyyh.ddnsagent.base.http.annotations.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import win.minaandyyh.ddnsagent.base.http.beans.OpenApiBeanDefinitionRegister;

import java.lang.annotation.*;

/**
 * @author 22454
 */
@Order(99)
@Documented
@Configuration
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({OpenApiBeanDefinitionRegister.class})
public @interface EnableOpenApi {
    String[] baseScanPackages();
}
