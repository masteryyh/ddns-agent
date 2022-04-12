package win.minaandyyh.ddnsagent;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.Application;

/**
 * Main class
 *
 * @author masteryyh
 */
@Component
@ComponentScan(basePackages = "win.minaandyyh.ddnsagent")
public class Main {
    public static void main(String[] args) {
        Application.run();
    }
}
