package win.minaandyyh.ddnsagent.base.http.annotations.api;

import win.minaandyyh.ddnsagent.base.http.enums.RequestType;

import java.lang.annotation.*;

/**
 * @author 22454
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Api {
    String url();

    RequestType method() default RequestType.GET;
}
