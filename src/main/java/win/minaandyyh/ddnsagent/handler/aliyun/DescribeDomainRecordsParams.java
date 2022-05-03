package win.minaandyyh.ddnsagent.handler.aliyun;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DescribeDomainRecordsParams extends BaseAliyunParams {
    @JsonProperty("DomainName")
    private String domainName;

    @JsonProperty("RRKeyWord")
    private String rrKeyword;

    @Builder.Default
    @JsonProperty("Type")
    private String type = "A";

    @Builder.Default
    @JsonProperty("Status")
    private String status = "Enable";

    @Builder.Default
    @JsonProperty("SearchMode")
    private String searchMode = "ADVANCED";
}
