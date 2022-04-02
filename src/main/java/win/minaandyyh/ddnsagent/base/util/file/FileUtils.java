package win.minaandyyh.ddnsagent.base.util.file;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Java NIO utilities
 *
 * @author masteryyh
 */
public class FileUtils {
    private FileUtils() {}

    /**
     * Null-safe check if a file path is valid
     *
     * @param path File path
     * @return True if this specific path is a valid file
     */
    public static boolean isValidPath(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }

        return Files.exists(Paths.get(path));
    }
}
