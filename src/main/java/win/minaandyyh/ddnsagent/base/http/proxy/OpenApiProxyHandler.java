package win.minaandyyh.ddnsagent.base.http.proxy;


import win.minaandyyh.ddnsagent.base.http.annotations.api.Api;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.clients.RequestClient;
import win.minaandyyh.ddnsagent.base.http.proxy.handlers.RequestHandlerFactory;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 22454
 */
public class OpenApiProxyHandler implements InvocationHandler {
    private final RequestHandlerFactory requestHandlerFactory;

    public OpenApiProxyHandler(RequestClient requestClient) {
        this.requestHandlerFactory = new RequestHandlerFactory(requestClient);
    }

    @Override
    public ApiResponse invoke(Object proxy, Method method, Object[] args) {
        try {
            return handle(method, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("RequestClient invoke < %s > failed.", method.getName()));
        }
    }

    private ApiResponse handle(Method method, Object[] args) {
        Api api = method.getAnnotation(Api.class);
        RequestType requestType = api.method();
        String url = api.url();
        return requestHandlerFactory.handler(requestType).handle(url, method, args);
    }
}