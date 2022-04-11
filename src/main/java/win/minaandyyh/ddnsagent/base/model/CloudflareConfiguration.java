package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import win.minaandyyh.ddnsagent.base.constant.ConfigurationConstants;
import win.minaandyyh.ddnsagent.base.constant.PatternConstants;

/**
 * Cloudflare configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareConfiguration implements ProviderConfiguration {
    @NotBlank(message = "missing X-Auth-Key or it's empty")
    @Pattern(regexp = PatternConstants.CLOUDFLARE_AUTH_KEY, message = "Cloudflare X-Auth-Key must meet regex: ^[a-z\\d]{37}$")
    private String authKey;

    @NotBlank(message = "missing X-Auth-Email or it's empty")
    @Email(message = "authEmail must be a valid email address")
    private String authEmail;

    @Override
    public void setValue(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }

        if (StringUtils.equals(key, ConfigurationConstants.CLOUDFLARE_AUTH_KEY)) {
            this.authKey = value;
        }

        if (StringUtils.equals(key, ConfigurationConstants.CLOUDFLARE_AUTH_EMAIL)) {
            this.authEmail = value;
        }
    }
}
