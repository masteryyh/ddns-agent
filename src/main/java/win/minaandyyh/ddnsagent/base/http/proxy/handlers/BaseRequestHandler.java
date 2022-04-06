package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
public abstract class BaseRequestHandler {
    protected RequestClient requestClient;

    public BaseRequestHandler(RequestClient requestClient) {
        this.requestClient = requestClient;
    }

    /**
     * handle
     *
     * @param url    url
     * @param method method
     * @param args   args
     * @return resp
     */
    public abstract ApiResponse handle(String url, Method method, Object[] args);

    /**
     * requestType
     *
     * @return resp
     */
    public abstract RequestType requestType();


    private Map<String, Map<String, Object>> extract(Method method, Object[] args) {
        Map<String, Map<String, Object>> map = new HashMap<>(2);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        //TODO 提取 header & params & payload
        return map;
    }
}
