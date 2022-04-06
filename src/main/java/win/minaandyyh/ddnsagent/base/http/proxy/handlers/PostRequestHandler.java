package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * @author 22454
 */
public class PostRequestHandler extends BaseRequestHandler {
    public PostRequestHandler(RequestClient requestClient) {
        super(requestClient);
    }

    @Override
    public ApiResponse handle(String url,
                              Map<String, String> header,
                              Map<String, Object> params,
                              Map<String, Object> payload) {
        return requestClient.post(url, header, params, payload);
    }

    @Override
    public RequestType requestType() {
        return RequestType.POST;
    }
}
