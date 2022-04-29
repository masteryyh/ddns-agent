package win.minaandyyh.ddnsagent.base.http.resp;

/**
 * HTTP response information (immutable)
 *
 * @author 22454
 * @author masteryyh
 */
public record ApiResponse(Integer httpStatus, String message, String body) {
}
