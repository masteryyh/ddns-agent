package win.minaandyyh.ddnsagent.base.model;

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
    private String authKey;

    private String authEmail;
}
