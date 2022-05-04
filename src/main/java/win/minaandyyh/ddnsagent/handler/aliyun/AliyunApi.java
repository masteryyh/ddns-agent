package win.minaandyyh.ddnsagent.handler.aliyun;

import win.minaandyyh.ddnsagent.base.http.annotations.api.Api;
import win.minaandyyh.ddnsagent.base.http.annotations.api.OpenApi;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Header;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Params;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Payload;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Map;

/**
 * Aliyun DNS HTTP service
 *
 * @author masteryyh
 */
@OpenApi
public interface AliyunApi {
    @Api(url = "https://dns.aliyuncs.com/", method = RequestType.GET)
    ApiResponse get(@Header Map<String, Object> header,
                       @Params Map<String, Object> params,
                       @Payload Map<String, Object> payload);

    @Api(url = "https://dns.aliyuncs.com/", method = RequestType.POST)
    ApiResponse update(@Header Map<String, Object> header,
                       @Params Map<String, Object> params,
                       @Payload Map<String, Object> payload);
}
