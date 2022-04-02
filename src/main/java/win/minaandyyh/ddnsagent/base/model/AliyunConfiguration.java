package win.minaandyyh.ddnsagent.base.model;

import jakarta.validation.constraints.NotEmpty;
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
    private String accessKeyId;

    @NotEmpty(message = "missing AccessKeySecret or it's empty")
    private String accessKeySecret;
}
