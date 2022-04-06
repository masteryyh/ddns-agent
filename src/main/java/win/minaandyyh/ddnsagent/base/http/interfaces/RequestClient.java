package win.minaandyyh.ddnsagent.base.http.interfaces;

import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;

import java.util.Map;

/**
 * @author 22454
 */
public interface RequestClient {

    /**
     * get request
     *
     * @param url url
     * @return resp
     */
    default ApiResponse get(String url) {
        return this.get(url, null);
    }

    /**
     * get request
     *
     * @param url    url
     * @param params params
     * @return resp
     */
    default ApiResponse get(String url, Map<String, Object> params) {
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
    ApiResponse get(String url, Map<String, Object> header, Map<String, Object> params);


    /**
     * post request
     *
     * @param url url
     * @return resp
     */
    default ApiResponse post(String url) {
        return this.post(url, null);
    }

    /**
     * post request
     *
     * @param url    url
     * @param header header
     * @return resp
     */
    default ApiResponse post(String url, Map<String, Object> header) {
        return this.post(url, header, null);
    }

    /**
     * post request
     *
     * @param url    url
     * @param header header
     * @param data   params
     * @return resp
     */
    ApiResponse post(String url, Map<String, Object> header, Map<String, Object> data);

    /**
     * put request
     *
     * @param url url
     * @return resp
     */
    default ApiResponse put(String url) {
        return this.put(url, null);
    }

    /**
     * put request
     *
     * @param url    url
     * @param header header
     * @return resp
     */
    default ApiResponse put(String url, Map<String, Object> header) {
        return this.put(url, header, null);
    }

    /**
     * put request
     *
     * @param url    url
     * @param header header
     * @param data   params
     * @return resp
     */
    ApiResponse put(String url, Map<String, Object> header, Map<String, Object> data);

    /**
     * delete request
     *
     * @param url url
     * @return resp
     */
    default ApiResponse delete(String url) {
        return this.delete(url, null);
    }


    /**
     * delete request
     *
     * @param url    url
     * @param header header
     * @return resp
     */
    default ApiResponse delete(String url, Map<String, Object> header) {
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
    ApiResponse delete(String url, Map<String, Object> header, Map<String, Object> params);
}
