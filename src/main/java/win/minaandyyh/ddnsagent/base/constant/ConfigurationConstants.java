package win.minaandyyh.ddnsagent.base.constant;

import java.util.List;

/**
 * Constants used in configuration files
 *
 * @author masteryyh
 */
public class ConfigurationConstants {
    private ConfigurationConstants() {}

    public static final String DOMAIN = "ddns.domain";

    public static final String SUBDOMAIN = "ddns.sub-domain";

    public static final String INTERVAL_UNIT = "ddns.interval-unit";

    public static final List<String> TIME_UNITS = List.of("NANOSECONDS", "MICROSECONDS", "MILLISECONDS", "SECONDS", "MINUTES", "HOURS", "DAYS");

    public static final String INTERVAL = "ddns.interval";

    public static final String PROVIDER = "ddns.provider";

    public static final List<String> VALID_PROVIDERS = List.of("ALIYUN", "CLOUDFLARE", "GODADDY", "DNSPOD");

    public static final String PROVIDER_SPECIFIC = "ddns.provider-specific";

    public static final String ALIYUN_ACCESS_KEY_ID = "ddns.provider-specific.aliyun.access-key-id";

    public static final String ALIYUN_ACCESS_KEY_SECRET = "ddns.provider-specific.aliyun.access-key-secret";

    public static final String CLOUDFLARE_AUTH_KEY = "ddns.provider-specific.cloudflare.auth-key";

    public static final String CLOUDFLARE_AUTH_EMAIL = "ddns.provider-specific.cloudflare.auth-email";

    public static final String DNSPOD_SECRET_ID = "ddns.provider-specific.dnspod.secret-id";

    public static final String DNSPOD_SECRET_KEY = "ddns.provider-specific.dnspod.secret-key";

    public static final String GO_DADDY_API_KEY = "ddns.provider-specific.godaddy.api-key";

    public static final String GO_DADDY_API_SECRET = "ddns.provider-specific.godaddy.api-secret";
}
