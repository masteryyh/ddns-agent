package win.minaandyyh.ddnsagent.base.model;

import lombok.Data;

/**
 * Tencent DNSPod configurations
 *
 * @author masteryyh
 */
@Data
public class DNSPodConfiguration {
    private String userAgent = "ddns-agent/1.0(yyh991013@163.com)";

    private String secretId;

    private String secretKey;
}
