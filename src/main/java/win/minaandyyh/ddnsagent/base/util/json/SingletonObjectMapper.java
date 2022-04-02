package win.minaandyyh.ddnsagent.base.util.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.ConcurrentInitializer;
import org.apache.commons.lang3.concurrent.LazyInitializer;

/**
 * Singleton ObjectMapper, since ObjectMapper is thread-safe then it just needs to be constructed once.
 * 
 * @author masteryyh
 */
public class SingletonObjectMapper {
    private SingletonObjectMapper() {}

    private static final ConcurrentInitializer<ObjectMapper> INITIALIZER = new LazyInitializer<>() {
        @Override
        protected ObjectMapper initialize() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper;
        }
    };

    public static ObjectMapper getInstance() {
        ObjectMapper mapper = null;
        try {
            mapper = INITIALIZER.get();
        } catch (ConcurrentException e) {
            e.printStackTrace();
            // TODO: Utilize log4j
        }
        return mapper;
    }
}
