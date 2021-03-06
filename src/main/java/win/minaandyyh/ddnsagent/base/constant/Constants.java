package win.minaandyyh.ddnsagent.base.constant;

/**
 * Useful constants
 *
 * @author masteryyh
 */
public class Constants {
    private Constants() {}

    public static final String ENV_VARIABLE = "DDNS_CONFIG_FILE";

    public static final String DEFAULT_CONFIG_LOCATION_LINUX = "/etc/ddns/config.json";

    public static final String DEFAULT_CONFIG_LOCATION_WINDOWS = "%APPDATA%\\ddns\\config.json";

    public static final String DDNS_TEST = "DDNS_TEST";

    public static final String EQUAL = "=";

    public static final String USER_AGENT = "ddns-agent/1.0(yyh991013@163.com)";

    public static final String EMPTY_STRING = "";

    public static final String AND = "&";

    public static final String SIGNATURE = "Signature";

    public static final String DESCRIBE_DOMAIN_RECORDS = "DescribeDomainRecords";

    public static final String ADD_DOMAIN_RECORD = "AddDomainRecord";

    public static final String UPDATE_DOMAIN_RECORD = "UpdateDomainRecord";
}
