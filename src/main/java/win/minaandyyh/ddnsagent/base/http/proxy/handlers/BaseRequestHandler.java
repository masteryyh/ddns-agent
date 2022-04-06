package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Header;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Params;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Payload;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 22454
 */
public abstract class BaseRequestHandler {
    protected static final String HEADER = "header";
    protected static final String PARAMS = "params";
    protected static final String PAYLOAD = "payload";
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
    public final ApiResponse handle(String url, Method method, Object[] args) {
        Map<String, Map<String, ?>> extract = extract(method, args);
        Map<String, String> header = (Map<String, String>) extract.get(HEADER);
        Map<String, Object> params = (Map<String, Object>) extract.get(PARAMS);
        Map<String, Object> payload = (Map<String, Object>) extract.get(PAYLOAD);
        return this.handle(url, header, params, payload);
    }

    /**
     * client handle
     *
     * @param url     url
     * @param header  header
     * @param params  params
     * @param payload payload
     * @return resp
     */
    protected abstract ApiResponse handle(String url,
                                          Map<String, String> header,
                                          Map<String, Object> params,
                                          Map<String, Object> payload);

    /**
     * requestType
     *
     * @return resp
     */
    public abstract RequestType requestType();


    protected Map<String, Map<String, ?>> extract(Method method, Object[] args) {
        Map<String, Map<String, ?>> map = new HashMap<>(2);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        // extract header & params & payload
        /*
         * [
         *      [
         *          Header
         *      ],
         *      [
         *          Params
         *      ],
         *      [
         *          Payload
         *      ]
         * ]
         */
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            Object arg = args[i];
            for (Annotation annotation : parameterAnnotation) {
                if (!(annotation instanceof Header) &&
                        !(annotation instanceof Params) &&
                        !(annotation instanceof Payload)) {
                    continue;
                }
                if (Objects.isNull(arg)) {
                    continue;
                } else if (!(arg instanceof Map)) {
                    throw new RuntimeException("Only parameters of type < Map > can be marked as < Header >");
                }

                if (annotation instanceof Header) {
                    map.put(HEADER, (Map<String, Object>) arg);
                } else if (annotation instanceof Params) {
                    map.put(PARAMS, (Map<String, Object>) arg);
                } else {
                    map.put(PAYLOAD, (Map<String, Object>) arg);
                }
            }
        }
        return map;
    }
}
