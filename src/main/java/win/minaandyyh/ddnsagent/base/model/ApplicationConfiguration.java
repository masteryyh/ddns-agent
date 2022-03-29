package win.minaandyyh.ddnsagent.base.model;

import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

/**
 * Application configuration properties model
 *
 * @author masteryyh
 */
@Data
@Builder
public class ApplicationConfiguration {
    private String domain;

    private String subDomain;

    private TimeUnit intervalUnit;

    private Long interval;

    @Nullable
    private AliyunConfiguration aliyun;

    @Nullable
    private CloudflareConfiguration cloudflare;

    @Nullable
    private GoDaddyConfiguration godaddy;

    @Nullable
    private DNSPodConfiguration dnspod;
}
