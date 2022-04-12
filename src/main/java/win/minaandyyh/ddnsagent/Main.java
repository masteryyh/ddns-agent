package win.minaandyyh.ddnsagent;

import org.springframework.context.annotation.ComponentScan;
import win.minaandyyh.ddnsagent.base.Application;

/**
 * Main class
 *
 * @author masteryyh
 */
@ComponentScan(basePackages = "win.minaandyyh.ddnsagent")
public class Main {
    public static void main(String[] args) {
        Application.run();
    }
}
