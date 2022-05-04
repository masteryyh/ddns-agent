package win.minaandyyh.ddnsagent.base.constant;

import java.util.Map;

/**
 * Public unchanged request headers.
 *
 * @author masteryyh
 */
public class RequestConstants {
    private RequestConstants() {}

    public static final Map<String, Object> ALIYUN_PARAMS = Map.of("Version", "2015-01-09",
            "Format", "JSON",
            "SignatureMethod", "HMAC-SHA1",
            "SignatureVersion", "1.0");
}
