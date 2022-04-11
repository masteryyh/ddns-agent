package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

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
    @Pattern(regexp = "^AKID[A-Za-z\\d]{32}$", message = "DNSPod secretId must meet regex: ^AKID[A-Za-z\\d]{32}$")
    private String secretId;

    @NotEmpty(message = "missing secretKey or it's empty")
    @Pattern(regexp = "^[A-Za-z\\d]{32}$", message = "DNSPod secretKey must meet regex: ^[A-Za-z\\d]{32}$")
    private String secretKey;

    @Override
    public void setValue(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.secret-id")) {
            this.secretId = value;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.secret-key")) {
            this.secretKey = value;
        }
    }
}
