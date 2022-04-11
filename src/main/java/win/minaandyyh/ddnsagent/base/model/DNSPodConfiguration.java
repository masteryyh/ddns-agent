package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import win.minaandyyh.ddnsagent.base.constant.PatternConstants;

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

    @NotBlank(message = "missing secretId or it's empty")
    @Pattern(regexp = PatternConstants.DNSPOD_SECRET_ID, message = "DNSPod secretId must meet regex: ^AKID[A-Za-z\\d]{32}$")
    private String secretId;

    @NotBlank(message = "missing secretKey or it's empty")
    @Pattern(regexp = PatternConstants.DNSPOD_SECRET_KEY, message = "DNSPod secretKey must meet regex: ^[A-Za-z\\d]{32}$")
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
