package win.minaandyyh.ddnsagent.base.model;

import lombok.*;

/**
 * Tencent DNSPod configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DNSPodConfiguration implements ProviderConfiguration {
    private String userAgent = "ddns-agent/1.0(yyh991013@163.com)";

    private String secretId;

    private String secretKey;
}
