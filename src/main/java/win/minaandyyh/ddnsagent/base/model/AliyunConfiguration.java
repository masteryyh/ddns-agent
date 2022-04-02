package win.minaandyyh.ddnsagent.base.model;

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
    private String accessKeyId;

    private String accessKeySecret;
}
