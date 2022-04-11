package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import win.minaandyyh.ddnsagent.base.constant.PatternConstants;

/**
 * Aliyun DNS configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliyunConfiguration implements ProviderConfiguration {
    @NotBlank(message = "missing AccessKeyId or it's empty")
    @Pattern(regexp = PatternConstants.ALIYUN_ACCESS_KEY_ID, message = "accessKeyId must meet the regex: ^[A-Za-z\\d]{24}$")
    private String accessKeyId;

    @NotBlank(message = "missing AccessKeySecret or it's empty")
    @Pattern(regexp = PatternConstants.ALIYUN_ACCESS_KEY_SECRET, message = "accessKeyId must meet the regex: ^[A-Za-z\\d]{30}$")
    private String accessKeySecret;

    @Override
    public void setValue(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.access-key-id")) {
            this.accessKeyId = value;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.access-key-secret")) {
            this.accessKeySecret = value;
        }
    }
}
