package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * Aliyun DNS configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliyunConfiguration implements ProviderConfiguration {
    @NotEmpty(message = "missing AccessKeyId or it's empty")
    @Pattern(regexp = "^[A-Za-z\\d]{24}$", message = "accessKeyId must meet the regex: ^[A-Za-z\\d]{24}$")
    private String accessKeyId;

    @NotEmpty(message = "missing AccessKeySecret or it's empty")
    @Pattern(regexp = "^[A-Za-z\\d]{30}$", message = "accessKeyId must meet the regex: ^[A-Za-z\\d]{30}$")
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
