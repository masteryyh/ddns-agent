package win.minaandyyh.ddnsagent.base.http.proxy.handlers;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import win.minaandyyh.ddnsagent.base.http.enums.HttpElement;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Header;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Params;
import win.minaandyyh.ddnsagent.base.http.annotations.parameter.Payload;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.clients.RequestClient;
import win.minaandyyh.ddnsagent.base.http.errors.HttpException;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;
import win.minaandyyh.ddnsagent.base.util.MyHttpEncodeUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import static win.minaandyyh.ddnsagent.base.http.enums.HttpElement.*;

/**
 * @author 22454
 */
public abstract class BaseRequestHandler {
    protected RequestClient requestClient;

    protected BaseRequestHandler(RequestClient requestClient) {
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
    @SuppressWarnings("unchecked")
    public final ApiResponse handle(String url, Method method, Object[] args) {
        Map<HttpElement, Map<String, ?>> extract = extract(method, args);

        Map<String, String> header = (Map<String, String>) extract.get(HEADER);
        Map<String, Object> params = (Map<String, Object>) extract.get(PARAMS);
        Map<String, Object> payload = (Map<String, Object>) extract.get(PAYLOAD);
        Map<String, Object> pathVariables = (Map<String, Object>) extract.get(PATH_VARIABLES);

        return this.handle(renderUrl(url, pathVariables), header, params, payload);
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

    @SuppressWarnings("unchecked")
    private Map<HttpElement, Map<String, ?>> extract(Method method, Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            return Collections.emptyMap();
        }

        Map<HttpElement, Map<String, ?>> elements = new EnumMap<>(HttpElement.class);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        // extract header & params & payload
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            Object arg = args[i];
            for (Annotation annotation : parameterAnnotation) {
                if (Objects.isNull(arg)) {
                    continue;
                } else if (!(arg instanceof Map)) {
                    throw HttpException.of("Only parameters of type < Map > can be marked as < Header >");
                }

                if (annotation instanceof Header) {
                    elements.put(HEADER, (Map<String, Object>) arg);
                } else if (annotation instanceof Params) {
                    elements.put(PARAMS, (Map<String, Object>) arg);
                } else if (annotation instanceof Payload) {
                    elements.put(PAYLOAD, (Map<String, Object>) arg);
                } else {
                    elements.put(PATH_VARIABLES, (Map<String, Object>) arg);
                }
            }
        }

        return elements;
    }

    private String renderUrl(String original, Map<String, Object> variables) {
        if (MapUtils.isEmpty(variables) || StringUtils.isBlank(original)) {
            return original;
        }

        String processed = original;
        for (Map.Entry<String, Object> variable : variables.entrySet()) {
            String name = variable.getKey();
            String value = MyHttpEncodeUtils.urlEncode(variable.getValue().toString());
            processed = processed.replaceAll("\\{" + name + "}", value);
        }

        return processed;
    }
}
