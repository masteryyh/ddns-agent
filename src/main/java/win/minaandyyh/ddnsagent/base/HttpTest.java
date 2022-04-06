package win.minaandyyh.ddnsagent.base;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import win.minaandyyh.ddnsagent.base.http.services.Test;

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

            System.out.println(service.test());
        } finally {
            if (applicationContext != null) {
                applicationContext.close();
                System.out.println("IOC容器关闭");
            }
        }
    }
}
