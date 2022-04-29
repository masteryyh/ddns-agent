package win.minaandyyh.ddnsagent.base.http.errors;

/**
 * @author 22454
 */
public class HttpException extends RuntimeException {

    private HttpException(String message) {
        super(message);
    }

    private HttpException(Throwable cause) {
        super(cause);
    }

    public static HttpException of(String message) {
        return new HttpException(message);
    }

    public static HttpException of(Throwable cause) {
        return new HttpException(cause);
    }
}
