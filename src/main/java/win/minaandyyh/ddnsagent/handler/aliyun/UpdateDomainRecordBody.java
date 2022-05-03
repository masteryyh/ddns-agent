package win.minaandyyh.ddnsagent.handler.aliyun;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDomainRecordBody {
    @JsonProperty("RecordId")
    private String recordId;

    @JsonProperty("RR")
    private String rr;

    @Builder.Default
    @JsonProperty("Type")
    private String type = "A";

    @JsonProperty("Value")
    private String value;
}
