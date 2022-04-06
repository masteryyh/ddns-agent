package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
public class RequestHandlerFactory {
    private final Map<RequestType, BaseRequestHandler> requestHandlerMap;

    public RequestHandlerFactory(RequestClient requestClient) {
        requestHandlerMap = new HashMap<>();
        this.init(requestClient);
    }

    private void init(RequestClient requestClient) {
        this.requestHandlerMap.put(RequestType.GET, new GetRequestHandler(requestClient));
        this.requestHandlerMap.put(RequestType.POST, new PostRequestHandler(requestClient));
        this.requestHandlerMap.put(RequestType.PUT, new PutRequestHandler(requestClient));
        this.requestHandlerMap.put(RequestType.DELETE, new DeleteRequestHandler(requestClient));
    }

    public BaseRequestHandler handler(RequestType requestType) {
        return requestHandlerMap.get(requestType);
    }
}
