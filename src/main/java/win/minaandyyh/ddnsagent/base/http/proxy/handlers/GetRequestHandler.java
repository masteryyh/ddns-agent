package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.lang.reflect.Method;


/**
 * @author 22454
 */
public class GetRequestHandler extends BaseRequestHandler {
    public GetRequestHandler(RequestClient requestClient) {
        super(requestClient);
    }

    @Override
    public ApiResponse handle(String url, Method method, Object[] args) {
        return null;
    }

    @Override
    public RequestType requestType() {
        return RequestType.GET;
    }
}
