package win.minaandyyh.ddnsagent.base.http.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 22454
 */
@Data
@Accessors(chain = true)
public class ApiResponse {
    private String httpStatus;
    private String message;
    private String body;
}
