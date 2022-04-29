package win.minaandyyh.ddnsagent.base.http.clients;

import org.apache.commons.collections4.MapUtils;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 22454
 */
public abstract class RequestClient {

    /**
     * get request
     *
     * @param url url
     * @return resp
     */
    public final ApiResponse get(String url) {
        return this.get(url, Collections.emptyMap());
    }

    /**
     * get request
     *
     * @param url    url
     * @param params params
     * @return resp
     */
    public final ApiResponse get(String url,
                                 Map<String, Object> params) {
        return this.get(url, Collections.emptyMap(), params);
    }

    /**
     * get request
     *
     * @param url    url
     * @param header header
     * @param params params
     * @return resp
     */
    public final ApiResponse get(String url,
                                 Map<String, String> header,
                                 Map<String, Object> params) {
        return this.get(url, header, params, Collections.emptyMap());
    }


    /**
     * get request
     *
     * @param url     url
     * @param header  header
     * @param params  params
     * @param payload payload
     * @return resp
     */
    public abstract ApiResponse get(String url,
                                    Map<String, String> header,
                                    Map<String, Object> params,
                                    Map<String, Object> payload);


    /**
     * post request
     *
     * @param url url
     * @return resp
     */
    public final ApiResponse post(String url) {
        return this.post(url, Collections.emptyMap());
    }

    /**
     * post request
     *
     * @param url    url
     * @param header header
     * @return resp
     */
    public final ApiResponse post(String url,
                                  Map<String, String> header) {
        return this.post(url, header, Collections.emptyMap());
    }

    /**
     * post request
     *
     * @param url    url
     * @param header header
     * @param params params
     * @return resp
     */
    public final ApiResponse post(String url,
                                  Map<String, String> header,
                                  Map<String, Object> params) {
        return this.post(url, header, params, Collections.emptyMap());
    }

    /**
     * post request
     *
     * @param url     url
     * @param header  header
     * @param params  params
     * @param payload payload
     * @return resp
     */
    public abstract ApiResponse post(String url,
                                     Map<String, String> header,
                                     Map<String, Object> params,
                                     Map<String, Object> payload);

    /**
     * put request
     *
     * @param url url
     * @return resp
     */
    public final ApiResponse put(String url) {
        return this.put(url, Collections.emptyMap());
    }

    /**
     * put request
     *
     * @param url    url
     * @param header header
     * @return resp
     */
    public final ApiResponse put(String url,
                                 Map<String, String> header) {
        return this.put(url, header, Collections.emptyMap());
    }

    /**
     * put request
     *
     * @param url    url
     * @param header header
     * @param params params
     * @return resp
     */
    public final ApiResponse put(String url,
                                 Map<String, String> header,
                                 Map<String, Object> params) {
        return this.put(url, header, params, Collections.emptyMap());
    }

    /**
     * put request
     *
     * @param url     url
     * @param header  header
     * @param params  params
     * @param payload payload
     * @return resp
     */
    public abstract ApiResponse put(String url,
                                    Map<String, String> header,
                                    Map<String, Object> params,
                                    Map<String, Object> payload);

    /**
     * delete request
     *
     * @param url url
     * @return resp
     */
    public final ApiResponse delete(String url) {
        return this.delete(url, Collections.emptyMap());
    }


    /**
     * delete request
     *
     * @param url    url
     * @param header header
     * @return resp
     */
    public final ApiResponse delete(String url,
                                    Map<String, String> header) {
        return this.delete(url, header, Collections.emptyMap());
    }

    /**
     * delete request
     *
     * @param url    url
     * @param header header
     * @param params params
     * @return resp
     */
    public final ApiResponse delete(String url,
                                    Map<String, String> header,
                                    Map<String, Object> params) {
        return this.delete(url, header, params, Collections.emptyMap());
    }

    /**
     * delete request
     *
     * @param url     url
     * @param header  header
     * @param params  params
     * @param payload payload
     * @return resp
     */
    public abstract ApiResponse delete(String url,
                                       Map<String, String> header,
                                       Map<String, Object> params,
                                       Map<String, Object> payload);

    protected String buildUrl(String url, Map<String, Object> params) {
        if (MapUtils.isEmpty(params)) {
            return url;
        }

        return url + params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue().toString())
                .collect(Collectors.joining("&", "?", ""));
    }

}
