package win.minaandyyh.ddnsagent.base.http.interfaces.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.io.IOException;
import java.util.Map;

/**
 * @author 22454
 */
@Component
public class OkHttpRequestClient extends RequestClient {
    private final OkHttpClient okHttpClient;
    private final ObjectMapper mapper;

    public OkHttpRequestClient(OkHttpClient okHttpClient,
                               ObjectMapper mapper) {
        this.okHttpClient = okHttpClient;
        this.mapper = mapper;
    }


    @Override
    public ApiResponse get(String url,
                           Map<String, String> header,
                           Map<String, Object> params,
                           Map<String, Object> payload) {
        String queryParams = emptyMap(params) ? "" : buildQueryParams(params);
        if (queryParams.trim().length() > 0) {
            url = url.concat("?").concat(queryParams);
        }

        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();

        if (!emptyMap(header)) {
            Headers headers = Headers.of(header);
            builder.headers(headers);
        }
        final Request request = builder
                .build();
        return call(request);
    }

    @Override
    public ApiResponse post(String url,
                            Map<String, String> header,
                            Map<String, Object> params,
                            Map<String, Object> payload) {


        String queryParams = emptyMap(params) ? "" : buildQueryParams(params);
        if (queryParams.trim().length() > 0) {
            url = url.concat("?").concat(queryParams);
        }

        String json = "";
        try {
            json = mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(
                json,
                MediaType.parse("application/json;charset=utf-8")
        );

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(requestBody);

        if (!emptyMap(header)) {
            Headers headers = Headers.of(header);
            builder.headers(headers);
        }
        final Request request = builder
                .build();
        return call(request);
    }

    @Override
    public ApiResponse put(String url,
                           Map<String, String> header,
                           Map<String, Object> params,
                           Map<String, Object> payload) {
        // TODO still under construction
        return null;
    }

    @Override
    public ApiResponse delete(String url,
                              Map<String, String> header,
                              Map<String, Object> params,
                              Map<String, Object> payload) {
        // TODO still under construction
        return null;
    }

    private ApiResponse call(Request request) {
        ApiResponse response = new ApiResponse();
        Call call = okHttpClient.newCall(request);
        try (Response resp = call.execute()) {
            response.setHttpStatus(String.valueOf(resp.code()))
                    .setMessage(resp.message())
                    .setBody(resp.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
