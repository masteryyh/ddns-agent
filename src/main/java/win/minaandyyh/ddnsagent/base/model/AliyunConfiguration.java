package win.minaandyyh.ddnsagent.base.model;

import lombok.Data;

/**
 * Aliyun DNS configurations
 *
 * @author masteryyh
 */
@Data
public class AliyunConfiguration {
    private String accessKeyId;

    private String accessKeySecret;
}
