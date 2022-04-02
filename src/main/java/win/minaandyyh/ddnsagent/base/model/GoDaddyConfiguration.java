package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "missing apiKey or it's empty")
    private String apiKey;

    @NotEmpty(message = "missing apiSecret or it's empty")
    private String apiSecret;
}
