package win.minaandyyh.ddnsagent.base.model;

import lombok.*;

/**
 * GoDaddy configurations
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoDaddyConfiguration implements ProviderConfiguration {
    private String apiKey;

    private String apiSecret;
}
