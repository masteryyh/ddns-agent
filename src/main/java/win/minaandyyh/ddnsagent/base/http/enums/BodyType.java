package win.minaandyyh.ddnsagent.base.http.enums;

import org.apache.commons.lang3.StringUtils;
import win.minaandyyh.ddnsagent.base.http.errors.HttpException;

import java.util.stream.Stream;

/**
 * HTTP request body type.
 *
 * @author masteryyh
 */
public enum BodyType {
    X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),

    JSON("application/json");

    private final String value;

    BodyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BodyType parseValue(String value) {
        return Stream.of(BodyType.class.getEnumConstants())
                .filter(type ->
                        StringUtils.equalsIgnoreCase(type.getValue(), value))
                .findAny()
                .orElseThrow(() -> HttpException.of("No such content-type: " + value));
    }
}
