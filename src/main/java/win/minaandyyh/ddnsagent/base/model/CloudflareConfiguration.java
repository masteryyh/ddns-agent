package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
    private String authKey;

    @NotEmpty(message = "missing X-Auth-Email or it's empty")
    @Email(message = "authEmail must be a valid email address")
    private String authEmail;
}
