package win.minaandyyh.ddnsagent.base.model;

/**
 * Public interface for DNS provider specific configuration properties
 *
 * @author masteryyh
 */
public interface ProviderConfiguration {
    /**
     * Set specific properties value from configuration file
     *
     * @param key Property key
     * @param value Property value
     */
    void setValue(String key, String value);
}
