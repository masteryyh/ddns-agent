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
}
