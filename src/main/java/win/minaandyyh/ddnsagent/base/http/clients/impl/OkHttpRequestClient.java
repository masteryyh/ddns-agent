package win.minaandyyh.ddnsagent.base.http.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.http.clients.RequestClient;
import win.minaandyyh.ddnsagent.base.http.enums.BodyType;
import win.minaandyyh.ddnsagent.base.http.errors.HttpException;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

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
        Request.Builder builder = new Request.Builder()
                .url(buildUrl(url, params))
                .get();

        if (MapUtils.isNotEmpty(header)) {
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
        Request.Builder builder = new Request.Builder()
                .url(buildUrl(url, params));

        if (MapUtils.isNotEmpty(header)) {
            String contentType = header.get("Content-Type");
            BodyType type = Objects.isNull(contentType) ? BodyType.JSON : BodyType.parseValue(contentType);
            builder.post(buildBody(payload, type));

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
        Request.Builder builder = new Request.Builder()
                .url(buildUrl(url, params));

        if (MapUtils.isNotEmpty(header)) {
            String contentType = header.get("Content-Type");
            BodyType type = Objects.isNull(contentType) ? BodyType.JSON : BodyType.parseValue(contentType);
            builder.put(buildBody(payload, type));

            Headers headers = Headers.of(header);
            builder.headers(headers);
        }

        final Request request = builder
                .build();
        return call(request);
    }

    @Override
    public ApiResponse delete(String url,
                              Map<String, String> header,
                              Map<String, Object> params,
                              Map<String, Object> payload) {
        Request.Builder builder = new Request.Builder()
                .url(buildUrl(url, params));

        if (MapUtils.isNotEmpty(header)) {
            if (MapUtils.isNotEmpty(payload)) {
                String contentType = header.get("Content-Type");
                BodyType type = Objects.isNull(contentType) ? BodyType.JSON : BodyType.parseValue(contentType);
                builder.delete(buildBody(payload, type));
            } else {
                builder.delete();
            }

            Headers headers = Headers.of(header);
            builder.headers(headers);
        }

        final Request request = builder
                .build();
        return call(request);
    }

    private ApiResponse call(Request request) {
        Call call = okHttpClient.newCall(request);
        try (Response resp = call.execute()) {
            return new ApiResponse(resp.code(), resp.message(),
                    Objects.isNull(resp.body()) ? "" : resp.body().string());
        } catch (IOException e) {
            throw HttpException.of(e);
        }
    }

    private RequestBody buildBody(Map<String, Object> payload, BodyType type) {
        if (Objects.isNull(type)) {
            throw HttpException.of("Must indicate request body type when building request body.");
        }

        if (MapUtils.isEmpty(payload)) {
            return RequestBody.create("", MediaType.parse(type.getValue()));
        }

        if (type.equals(BodyType.X_WWW_FORM_URLENCODED)) {
            FormBody.Builder builder = new FormBody.Builder();
            payload.forEach((key, value) -> builder.addEncoded(key, value.toString()));
            return builder.build();
        }

        String json;
        try {
            json = mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw HttpException.of(e);
        }

        MediaType mediaType = MediaType.parse("application/json;charset=utf8");
        return RequestBody.create(json, mediaType);
    }
}
