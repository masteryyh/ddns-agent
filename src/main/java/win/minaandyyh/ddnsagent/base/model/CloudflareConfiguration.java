package win.minaandyyh.ddnsagent.base.model;

import lombok.Data;

/**
 * Cloudflare configurations
 *
 * @author masteryyh
 */
@Data
public class CloudflareConfiguration {
    private String authKey;

    private String authEmail;
}
