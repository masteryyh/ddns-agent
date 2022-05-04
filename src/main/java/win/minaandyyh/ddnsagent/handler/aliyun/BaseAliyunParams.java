package win.minaandyyh.ddnsagent.handler.aliyun;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import win.minaandyyh.ddnsagent.base.util.DateTimeUtils;

import java.util.UUID;

@Data
@SuperBuilder
public class BaseAliyunParams {
    @Builder.Default
    @JsonProperty("Version")
    protected String version = "2015-01-09";

    @Builder.Default
    @JsonProperty("Format")
    protected String format = "JSON";

    @Builder.Default
    @JsonProperty("SignatureMethod")
    protected String signatureMethod = "HMAC-SHA1";

    @Builder.Default
    @JsonProperty("SignatureVersion")
    protected String signatureVersion = "1.0";
    
    @JsonProperty("Action")
    protected String action;
    
    @JsonProperty("AccessKeyId")
    protected String accessKeyId;

    @Builder.Default
    @JsonProperty("SignatureNonce")
    protected String signatureNonce = UUID.randomUUID().toString();

    @Builder.Default
    @JsonProperty("Timestamp")
    protected String timestamp = DateTimeUtils.getUTCString();
    
    @JsonProperty("Signature")
    protected String signature;
}
