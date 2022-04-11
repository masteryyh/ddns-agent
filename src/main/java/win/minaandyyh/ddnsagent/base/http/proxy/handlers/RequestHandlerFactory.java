package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.clients.RequestClient;

import java.util.EnumMap;

/**
 * @author 22454
 */
public class RequestHandlerFactory {
    private final EnumMap<RequestType, BaseRequestHandler> requestHandlerMap;

    public RequestHandlerFactory(RequestClient requestClient) {
        requestHandlerMap = new EnumMap<>(RequestType.class);
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
