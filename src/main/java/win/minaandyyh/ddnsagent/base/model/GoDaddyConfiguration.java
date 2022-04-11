package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import win.minaandyyh.ddnsagent.base.constant.PatternConstants;

/**
 * GoDaddy configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoDaddyConfiguration implements ProviderConfiguration {
    @NotBlank(message = "missing apiKey or it's empty")
    @Pattern(regexp = PatternConstants.GO_DADDY_API_KEY, message = "GoDaddy apiKey must meet regex: ^[A-Za-z\\d]{14}_[A-Za-z\\d]{22}$")
    private String apiKey;

    @NotBlank(message = "missing apiSecret or it's empty")
    @Pattern(regexp = PatternConstants.GO_DADDY_API_SECRET, message = "GoDaddy apiSecret must meet regex: ^[A-Za-z\\d]{22}$")
    private String apiSecret;

    @Override
    public void setValue(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.api-key")) {
            this.apiKey = value;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.api-secret")) {
            this.apiSecret = value;
        }
    }
}
