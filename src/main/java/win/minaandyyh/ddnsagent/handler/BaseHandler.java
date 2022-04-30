package win.minaandyyh.ddnsagent.handler;

import lombok.extern.slf4j.Slf4j;
import win.minaandyyh.ddnsagent.base.errors.ApplicationException;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Collections;
import java.util.Objects;

/**
 * Basic abstract handler class
 *
 * @author masteryyh
 */
@Slf4j
public abstract class BaseHandler implements Handler {
    private final PublicIPApi ipApi;

    protected BaseHandler(PublicIPApi ipApi) {
        this.ipApi = ipApi;
    }

    protected String currentIP() {
        ApiResponse response = ipApi.getIP(Collections.emptyMap(), Collections.emptyMap(), Collections.emptyMap());
        if (!Objects.equals(response.httpStatus(), 200)) {
            throw ApplicationException.of("Error occurred when requesting current IP address, status code: "
                    + response.httpStatus() + ", message: " + response.message());
        }

        String ip = response.message();
        log.info("Current IP address: {}", ip);
        return ip;
    }

    public abstract void handle() throws Exception;
}
