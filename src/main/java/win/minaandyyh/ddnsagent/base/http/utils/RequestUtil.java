package win.minaandyyh.ddnsagent.base.http.utils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import win.minaandyyh.ddnsagent.base.http.annotations.api.Api;
import win.minaandyyh.ddnsagent.base.http.annotations.api.OpenApi;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author 22454
 */
public class RequestUtil {
    private static final OkHttpClient client;

    static {
        client = SpringContextUtil.getBean(OkHttpClient.class);
    }

    private RequestUtil() {
    }

    public static String get(String url, Map<String, Object> params) {
        url = String.format("%s?%s", url, buildQueryParams(params));
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("failed");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println(response);
//            }
//        });
//        return request.toString();
        try {
            Response execute = call.execute();
            return Objects.requireNonNull(execute.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String buildQueryParams(Map<String, Object> params) {
        if (Objects.isNull(params) || params.size() <= 0) {
            return "";
        }
        final StringBuilder urlBuilder = new StringBuilder();
        params.forEach((k, v) -> urlBuilder.append(k).append("=").append(v).append("&"));
        int length = urlBuilder.length();
        return urlBuilder.deleteCharAt(length - 1).toString();
    }

    public static Object handleApiAnnotation(Object proxy, Method method, Object[] args) {
        OpenApi openApi = proxy.getClass().getAnnotation(OpenApi.class);
        Api api = method.getAnnotation(Api.class);
        return "success";
    }

    public static Object apiFallback(Class<?> fallbackClass, Method method, Object[] args) {
        try {
            return method.invoke(fallbackClass, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
