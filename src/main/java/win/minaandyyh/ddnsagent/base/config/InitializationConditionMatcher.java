package win.minaandyyh.ddnsagent.base.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import win.minaandyyh.ddnsagent.base.constant.Constants;

/**
 * Check if current execution is for unit test
 *
 * @author masteryyh
 */
public class InitializationConditionMatcher implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return StringUtils.isEmpty(System.getProperty(Constants.DDNS_TEST));
    }
}
