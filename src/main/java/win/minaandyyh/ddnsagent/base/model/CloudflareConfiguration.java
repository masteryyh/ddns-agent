package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * Cloudflare configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareConfiguration implements ProviderConfiguration {
    @NotEmpty(message = "missing X-Auth-Key or it's empty")
    @Pattern(regexp = "^[a-z\\d]{37}$", message = "Cloudflare X-Auth-Key must meet regex: ^[a-z\\d]{37}$")
    private String authKey;

    @NotEmpty(message = "missing X-Auth-Email or it's empty")
    @Email(message = "authEmail must be a valid email address")
    private String authEmail;

    @Override
    public void setValue(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.auth-key")) {
            this.authKey = value;
        }

        if (StringUtils.equals(key, "ddns.provider-specific.auth-email")) {
            this.authEmail = value;
        }
    }
}
