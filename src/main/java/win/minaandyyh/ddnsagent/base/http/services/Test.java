package win.minaandyyh.ddnsagent.base.http.services;

import win.minaandyyh.ddnsagent.base.http.annotations.api.Api;
import win.minaandyyh.ddnsagent.base.http.annotations.api.OpenApi;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Header;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Params;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Payload;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Map;

/**
 * @author 22454
 */
@OpenApi
public interface Test {
    /**
     * test
     *
     * @param header  header
     * @param params  params
     * @param payload payload
     * @param u       u
     * @return resp
     */
    @Api(url = "http://www.baidu.com", method = RequestType.GET)
    ApiResponse test(@Header Map<String, Object> header,
                     @Params Map<String, Object> params,
                     @Payload Map<String, Object> payload,
                     String u);
}
