package win.minaandyyh.ddnsagent.base.util.config;

import jakarta.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.model.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * .conf file reader
 *
 * @author masteryyh
 */
@Component("conf")
public class ConfConfigurationReader extends AbstractConfigurationReader implements ConfigurationReader {
    private final Map<DNSProvider, ProviderConfiguration> providerConfigurations = new EnumMap<>(DNSProvider.class);

    @Autowired
    public ConfConfigurationReader(Validator validator) {
        super(validator);
        providerConfigurations.put(DNSProvider.ALIYUN, new AliyunConfiguration());
        providerConfigurations.put(DNSProvider.CLOUDFLARE, new CloudflareConfiguration());
        providerConfigurations.put(DNSProvider.DNSPOD, new DNSPodConfiguration());
        providerConfigurations.put(DNSProvider.GODADDY, new GoDaddyConfiguration());
    }

    private boolean isLineInvalid(String line) {
        return StringUtils.isEmpty(line) || line.split("=").length != 2;
    }

    private void readBasicConfig(String line, ApplicationConfiguration configuration) {
        if (isLineInvalid(line) || Objects.isNull(configuration)) {
            return;
        }

        String[] parts = line.split("=");
        String key = parts[0];
        String value = parts[1];
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }

        if (StringUtils.equals(key, "ddns.domain") && StringUtils.isEmpty(configuration.getDomain())) {
            configuration.setDomain(value);
        }

        if (StringUtils.equals(key, "ddns.sub-domain") && StringUtils.isEmpty(configuration.getSubDomain())) {
            configuration.setSubDomain(value);
        }

        if (StringUtils.equals(key, "ddns.interval-unit") && Objects.isNull(configuration.getIntervalUnit())) {
            if (StringUtils.equalsAny(value, "NANOSECONDS", "MICROSECONDS", "MILLISECONDS", "SECONDS", "MINUTES", "HOURS", "DAYS")) {
                throw new IllegalArgumentException("Invalid time unit " + value);
            }
            configuration.setIntervalUnit(TimeUnit.valueOf(value));
        }

        if (StringUtils.equals(key, "ddns.interval") && StringUtils.isNumeric(value)) {
            configuration.setInterval(Long.getLong(value));
        }

        if (StringUtils.equals(key, "ddns.provider") && Objects.isNull(configuration.getProvider())) {
            if (StringUtils.equalsAny(value, "ALIYUN", "CLOUDFLARE", "GODADDY", "DNSPOD")) {
                return;
            }
            DNSProvider provider = DNSProvider.valueOf(value);
            configuration.setProvider(provider);
            configuration.setProviderSpecific(providerConfigurations.get(provider));
        }
    }

    private void readSpecificConfig(String line, ApplicationConfiguration configuration) {
        if (Objects.isNull(configuration) || Objects.isNull(configuration.getProvider()) || Objects.isNull(configuration.getProviderSpecific())) {
            return;
        }

        String[] parts = line.split("=");
        String key = parts[0];
        String value = parts[1];
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }

        switch (configuration.getProvider()) {
            case ALIYUN -> {
                AliyunConfiguration aliyunConfig = (AliyunConfiguration) configuration.getProviderSpecific();
                if (StringUtils.equals(key, "ddns.aliyun.access-key-id") && StringUtils.isEmpty(aliyunConfig.getAccessKeyId())) {
                    aliyunConfig.setAccessKeyId(value);
                }

                if (StringUtils.equals(key, "ddns.aliyun.access-key-secret") && StringUtils.isEmpty(aliyunConfig.getAccessKeySecret())) {
                    aliyunConfig.setAccessKeySecret(value);
                }
                configuration.setProviderSpecific(aliyunConfig);
            }
            case CLOUDFLARE -> {
                CloudflareConfiguration cloudflareConfig = (CloudflareConfiguration) configuration.getProviderSpecific();
                if (StringUtils.equals(key, "ddns.cloudflare.auth-key") && StringUtils.isEmpty(cloudflareConfig.getAuthKey())) {
                    cloudflareConfig.setAuthKey(value);
                }

                if (StringUtils.equals(key, "ddns.cloudflare.auth-email") && StringUtils.isEmpty(cloudflareConfig.getAuthEmail())) {
                    cloudflareConfig.setAuthEmail(value);
                }
                configuration.setProviderSpecific(cloudflareConfig);
            }
            case DNSPOD -> {
                DNSPodConfiguration dnspodConfig = (DNSPodConfiguration) configuration.getProviderSpecific();
                if (StringUtils.equals(key, "ddns.dnspod.secret-id") && StringUtils.isEmpty(dnspodConfig.getSecretId())) {
                    dnspodConfig.setSecretId(value);
                }

                if (StringUtils.equals(key, "ddns.dnspod.secret-key") && StringUtils.isEmpty(dnspodConfig.getSecretKey())) {
                    dnspodConfig.setSecretKey(value);
                }
                configuration.setProviderSpecific(dnspodConfig);
            }
            case GODADDY -> {
                GoDaddyConfiguration goDaddyConfig = (GoDaddyConfiguration) configuration.getProviderSpecific();
                if (StringUtils.equals(key, "ddns.godaddy.api-key") && StringUtils.isEmpty(goDaddyConfig.getApiKey())) {
                    goDaddyConfig.setApiKey(value);
                }

                if (StringUtils.equals(key, "ddns.godaddy.api-secret") && StringUtils.isEmpty(goDaddyConfig.getApiSecret())) {
                    goDaddyConfig.setApiSecret(value);
                }
                configuration.setProviderSpecific(goDaddyConfig);
            }
        }
    }

    @Override
    public Optional<ApplicationConfiguration> read(CharSequence path) throws Exception {
        String config = Files.readString(Paths.get(path.toString()));
        if (StringUtils.isEmpty(config)) {
            throw new IllegalArgumentException("Empty configuration.");
        }

        String[] lines = config.split("\n");
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        Arrays.stream(lines).forEachOrdered(line -> {
            readBasicConfig(line, configuration);
            readSpecificConfig(line, configuration);
        });

        validate(configuration);
        return Optional.of(configuration);
    }
}
