package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * GoDaddy configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoDaddyConfiguration implements ProviderConfiguration {
    @NotEmpty(message = "missing apiKey or it's empty")
    @Pattern(regexp = "^[A-Za-z\\d]{14}_[A-Za-z\\d]{22}$", message = "GoDaddy apiKey must meet regex: ^[A-Za-z\\d]{14}_[A-Za-z\\d]{22}$")
    private String apiKey;

    @NotEmpty(message = "missing apiSecret or it's empty")
    @Pattern(regexp = "^[A-Za-z\\d]{22}$", message = "GoDaddy apiSecret must meet regex: ^[A-Za-z\\d]{22}$")
    private String apiSecret;

    @Override
    public void setValue(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
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
