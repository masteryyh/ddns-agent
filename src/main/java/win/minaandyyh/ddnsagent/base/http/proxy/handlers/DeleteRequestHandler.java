package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.clients.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Map;


/**
 * @author 22454
 */
public class DeleteRequestHandler extends BaseRequestHandler {

    public DeleteRequestHandler(RequestClient requestClient) {
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
        return RequestType.DELETE;
    }
}
