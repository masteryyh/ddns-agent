package win.minaandyyh.ddnsagent.base;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import win.minaandyyh.ddnsagent.base.http.services.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
@ComponentScan(basePackages = "win.minaandyyh.ddnsagent")
public class HttpTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = null;
        try {
            applicationContext = new AnnotationConfigApplicationContext(HttpTest.class);
            Test service = applicationContext.getBean(Test.class);

            Map<String, Object> params = new HashMap<>(2);
            params.put("q", "redis");
            params.put("type", "Code");
            System.out.println(service.test(null, params, null, "123"));
        } finally {
            if (applicationContext != null) {
                applicationContext.close();
                System.out.println("IOC容器关闭");
            }
        }
    }
}
