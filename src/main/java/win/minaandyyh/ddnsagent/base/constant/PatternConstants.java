package win.minaandyyh.ddnsagent.base.constant;

/**
 * Regex pattern constants
 *
 * @author masteryyh
 */
public class PatternConstants {
    private PatternConstants() {}

    public static final String ALIYUN_ACCESS_KEY_ID = "^[A-Za-z\\d]{24}$";

    public static final String ALIYUN_ACCESS_KEY_SECRET = "^[A-Za-z\\d]{30}$";

    public static final String ALIYUN_RECORD_ID = "^\\d{7}$";

    public static final String CLOUDFLARE_AUTH_KEY = "^[a-z\\d]{37}$";

    public static final String DNSPOD_SECRET_ID = "^AKID[A-Za-z\\d]{32}$";

    public static final String DNSPOD_SECRET_KEY = "^[A-Za-z\\d]{32}$";

    public static final String GO_DADDY_API_KEY = "^[A-Za-z\\d]{14}_[A-Za-z\\d]{22}$";

    public static final String GO_DADDY_API_SECRET = "^[A-Za-z\\d]{22}$";
}
