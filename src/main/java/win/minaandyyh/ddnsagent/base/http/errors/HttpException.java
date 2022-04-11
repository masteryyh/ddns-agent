package win.minaandyyh.ddnsagent.base.http.errors;

/**
 * @author 22454
 */
public class HttpException extends RuntimeException {

    private HttpException(String message) {
        super(message);
    }

    public static HttpException of(String message) {
        return new HttpException(message);
    }
}
