package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Za-z\\d]{14}_[A-Za-z\\d]{22}$", message = "GoDaddy apiKey must meet regex: ^[A-Za-z\\d]{14}_[A-Za-z\\d]{22}$")
    private String apiKey;

    @NotEmpty(message = "missing apiSecret or it's empty")
    @Pattern(regexp = "^[A-Za-z\\d]{22}$", message = "GoDaddy apiSecret must meet regex: ^[A-Za-z\\d]{22}$")
    private String apiSecret;
}
