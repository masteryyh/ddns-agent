package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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
}
