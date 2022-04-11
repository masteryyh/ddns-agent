package win.minaandyyh.ddnsagent.base.util.config;

import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;

import java.util.Optional;

/**
 * Configuration file reader
 *
 * @author masteryyh
 */
public interface ConfigurationReader {
    /**
     * Read configuration file from a certain path
     *
     * @param path File path
     * @return Parsed configuration, or empty {@link Optional} when path is invalid or file does not exist
     * @throws Exception Throws when error occurred reading or parsing file
     */
    Optional<ApplicationConfiguration> read(CharSequence path) throws Exception;
}
