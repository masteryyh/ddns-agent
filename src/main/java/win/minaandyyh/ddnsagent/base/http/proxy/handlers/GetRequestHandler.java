package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import lombok.extern.slf4j.Slf4j;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;


/**
 * @author 22454
 */
@Slf4j
public class GetRequestHandler extends BaseRequestHandler {
    public GetRequestHandler(RequestClient requestClient) {
        super(requestClient);
    }

    @Override
    public ApiResponse handle(String url,
                              Map<String, String> header,
                              Map<String, Object> params,
                              Map<String, Object> payload) {
        return requestClient.get(url, header, params, payload);
    }

    @Override
    public RequestType requestType() {
        return RequestType.GET;
    }
}
