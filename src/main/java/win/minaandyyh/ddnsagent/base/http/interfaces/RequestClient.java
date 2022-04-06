package win.minaandyyh.ddnsagent.base.http.interfaces;

import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Map;
import java.util.Objects;

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
        return this.get(url, null);
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
        return this.get(url, null, params);
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
        return this.get(url, header, params, null);
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
        return this.post(url, null);
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
        return this.post(url, header, null);
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
        return this.post(url, header, params, null);
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
        return this.put(url, null);
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
        return this.put(url, header, null);
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
        return this.put(url, header, params, null);
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
        return this.delete(url, null);
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
        return this.delete(url, header, null);
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
        return this.delete(url, header, params, null);
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

    protected String buildQueryParams(Map<String, Object> params) {
        if (Objects.isNull(params) || params.size() <= 0) {
            return "";
        }
        final StringBuilder urlBuilder = new StringBuilder();
        params.forEach((k, v) -> urlBuilder.append(k).append("=").append(v).append("&"));
        int length = urlBuilder.length();
        return urlBuilder.deleteCharAt(length - 1).toString();
    }

    /**
     * 判断 map 是否为空
     *
     * @param map map
     * @return res
     */
    protected <K, V> boolean emptyMap(Map<K, V> map) {
        return Objects.isNull(map) || map.size() == 0;
    }
}
