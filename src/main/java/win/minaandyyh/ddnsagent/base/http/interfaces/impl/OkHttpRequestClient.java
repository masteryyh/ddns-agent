package win.minaandyyh.ddnsagent.base.http.interfaces.impl;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Map;

/**
 * @author 22454
 */
@Component
public class OkHttpRequestClient implements RequestClient {
    private final OkHttpClient okHttpClient;

    public OkHttpRequestClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public ApiResponse get(String url, Map<String, Object> header, Map<String, Object> params) {
        return null;
    }

    @Override
    public ApiResponse post(String url, Map<String, Object> header, Map<String, Object> data) {
        return null;
    }

    @Override
    public ApiResponse put(String url, Map<String, Object> header, Map<String, Object> data) {
        return null;
    }

    @Override
    public ApiResponse delete(String url, Map<String, Object> header, Map<String, Object> params) {
        return null;
    }
}
