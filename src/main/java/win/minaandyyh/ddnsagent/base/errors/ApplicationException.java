package win.minaandyyh.ddnsagent.base.errors;

/**
 * @author 22454
 */
public class ApplicationException extends RuntimeException {

    private ApplicationException(String message) {
        super(message);
    }

    public static ApplicationException of(String message) {
        return new ApplicationException(message);
    }
}
