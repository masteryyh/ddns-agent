package win.minaandyyh.ddnsagent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Main class
 *
 * @author masteryyh
 */
@ComponentScan(basePackages = "win.minaandyyh.ddnsagent")
public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
            System.out.println(context.getApplicationName());
        }
    }
}
