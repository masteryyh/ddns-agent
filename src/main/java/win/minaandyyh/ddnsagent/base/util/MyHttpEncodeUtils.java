package win.minaandyyh.ddnsagent.base.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import win.minaandyyh.ddnsagent.base.constant.Constants;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * My additional utilities about string literals in HTTP.
 *
 * @author masteryyh
 */
public class MyHttpEncodeUtils {
    private MyHttpEncodeUtils() {}

    /**
     * Encode specific plain string into application/x-www-form-urlencoded format using UTF-8.
     * 
     * @param original String to encode
     * @return Encoded string
     */
    public static String urlEncode(String original) {
        if (StringUtils.isEmpty(original)) {
            return Constants.EMPTY_STRING;
        }
        
        return URLEncoder.encode(original, StandardCharsets.UTF_8);
    }

    /**
     * Concatenate all URL params with '&' and '=' after encoded.
     *
     * @param params URL params
     * @return Concatenated param string
     */
    public static String httpParamToString(Map<String, Object> params) {
        if (MapUtils.isEmpty(params)) {
            return Constants.EMPTY_STRING;
        }

        return params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + urlEncode(entry.getValue().toString()))
                .collect(Collectors.joining("&"));
    }
}
