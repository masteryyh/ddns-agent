package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "missing secretId or it's empty")
    private String secretId;

    @NotEmpty(message = "missing secretKey or it's empty")
    private String secretKey;
}
