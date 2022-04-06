package win.minaandyyh.ddnsagent.base.http.services;

import win.minaandyyh.ddnsagent.base.http.annotations.api.Api;
import win.minaandyyh.ddnsagent.base.http.annotations.api.OpenApi;

/**
 * @author 22454
 */
@OpenApi
public interface Test {
    /**
     * test
     */
    @Api(url = "http://www.baidu.com")
    String test();
}
