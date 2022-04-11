package win.minaandyyh.ddnsagent.base.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import win.minaandyyh.ddnsagent.base.validators.ValidTimeUnit;

import java.util.concurrent.TimeUnit;

/**
 * Application configuration properties model
 *
 * @author masteryyh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationConfiguration {
    @NotBlank(message = "domain cannot be null.")
    private String domain;

    @NotBlank(message = "subdomain cannot be null, empty subdomain should be configured as '@'")
    private String subDomain;

    @NotNull(message = "interval time unit cannot be null")
    @ValidTimeUnit(anyOf = {
            TimeUnit.HOURS,
            TimeUnit.DAYS
    })
    private TimeUnit intervalUnit;

    @Positive(message = "interval must be bigger than 0")
    private Long interval;

    @NotNull(message = "DNS provider name cannot be null or empty")
    private DNSProvider provider;

    @Valid
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "provider")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = AliyunConfiguration.class, name = "ALIYUN"),
        @JsonSubTypes.Type(value = CloudflareConfiguration.class, name = "CLOUDFLARE"),
        @JsonSubTypes.Type(value = DNSPodConfiguration.class, name = "DNSPOD"),
        @JsonSubTypes.Type(value = GoDaddyConfiguration.class, name = "GODADDY")
    })
    private ProviderConfiguration providerSpecific;
}
