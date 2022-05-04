package win.minaandyyh.ddnsagent.handler;

import win.minaandyyh.ddnsagent.base.http.annotations.api.Api;
import win.minaandyyh.ddnsagent.base.http.annotations.api.OpenApi;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Header;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Params;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Payload;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Map;

/**
 * API for fetching current public IP address.
 *
 * @author masteryyh
 */
@OpenApi
public interface PublicIPApi {
    @Api(url = "https://api.ipify.org", method = RequestType.GET)
    ApiResponse getIP(@Header Map<String, Object> header,
                      @Params Map<String, Object> params,
                      @Payload Map<String, Object> payload);
}
