package win.minaandyyh.ddnsagent.handler.aliyun;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response structure when querying AliDNS DNS records.
 *
 * @author masteryyh
 */
public record AliyunRecordGetResponse(@JsonProperty("TotalCount") Long totalCount,
                                      @JsonProperty("PageSize") Long pageSize,
                                      @JsonProperty("RequestId") String requestId,
                                      @JsonProperty("PageNumber") Long pageNumber,
                                      @JsonProperty("DomainRecords") List<AliyunDNSRecord> domainRecords) {
    public record AliyunDNSRecord(@JsonProperty("Status") String status,
                                  @JsonProperty("Type") String type,
                                  @JsonProperty("Remark") String remark,
                                  @JsonProperty("TTL") Long ttl,
                                  @JsonProperty("RecordId") String recordId,
                                  @JsonProperty("Priority") Long priority,
                                  @JsonProperty("RR") String rr,
                                  @JsonProperty("DomainName") String domainName,
                                  @JsonProperty("Weight") Integer weight,
                                  @JsonProperty("Value") String value,
                                  @JsonProperty("Line") String line,
                                  @JsonProperty("Locked") Boolean locked) {}
}
