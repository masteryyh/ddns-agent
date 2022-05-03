package win.minaandyyh.ddnsagent.handler.aliyun;

import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import win.minaandyyh.ddnsagent.base.constant.Constants;
import win.minaandyyh.ddnsagent.base.errors.ApplicationException;
import win.minaandyyh.ddnsagent.base.http.enums.RequestType;
import win.minaandyyh.ddnsagent.base.http.resp.ApiResponse;
import win.minaandyyh.ddnsagent.base.model.AliyunConfiguration;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;
import win.minaandyyh.ddnsagent.base.util.MyStringUtils;
import win.minaandyyh.ddnsagent.handler.BaseHandler;
import win.minaandyyh.ddnsagent.handler.Handler;
import win.minaandyyh.ddnsagent.handler.PublicIPApi;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Aliyun update handler.
 *
 * @author masteryyh
 */
@Service
@Slf4j
public class AliyunHandler extends BaseHandler implements Handler {
    private final ApplicationConfiguration configuration;

    private final AliyunApi aliyunApi;

    private final ObjectMapper mapper;

    public AliyunHandler(ApplicationConfiguration configuration, PublicIPApi ipApi, AliyunApi aliyunApi, ObjectMapper mapper) {
        super(ipApi);

        this.configuration = configuration;
        this.aliyunApi = aliyunApi;
        this.mapper = mapper;
    }

    private String sign(RequestType type, String queryString, String secret) {
        String plaintext = type.name() + Constants.AND +
                MyStringUtils.urlEncode("/") + Constants.AND +
                MyStringUtils.urlEncode(queryString);

        return SecureUtil.hmacSha1(secret)
                .digestBase64(plaintext, StandardCharsets.UTF_8, false);
    }

    private void fillInSignature(Map<String, Object> params, Map<String, Object> body, String secret, RequestType type) {
        Map<String, Object> canonicalQueryPart = new LinkedHashMap<>();
        canonicalQueryPart.putAll(params);
        canonicalQueryPart.putAll(body);
        String signature = sign(type, MyStringUtils.httpParamToString(canonicalQueryPart), secret);
        params.put(Constants.SIGNATURE, signature);
    }

    private AliyunRecordGetResponse currentRecords() throws JsonProcessingException {
        String accessKeyId = ((AliyunConfiguration) configuration.getProviderSpecific()).getAccessKeyId();
        String accessKeySecret = ((AliyunConfiguration) configuration.getProviderSpecific()).getAccessKeySecret();

        DescribeDomainRecordsParams paramObj = DescribeDomainRecordsParams.builder()
                .action(Constants.DESCRIBE_DOMAIN_RECORDS)
                .accessKeyId(accessKeyId)
                .domainName(configuration.getDomain())
                .rrKeyword(configuration.getSubDomain())
                .build();
        Map<String, Object> params = mapper.convertValue(paramObj, new TypeReference<>() {});

        fillInSignature(params, Collections.emptyMap(), accessKeySecret, RequestType.GET);
        ApiResponse response = aliyunApi.get(Collections.emptyMap(), params, Collections.emptyMap());
        return mapper.readValue(response.body(), AliyunRecordGetResponse.class);
    }

    private void create() {
        String currentIP = currentIP();
        String accessKeyId = ((AliyunConfiguration) configuration.getProviderSpecific()).getAccessKeyId();
        String accessKeySecret = ((AliyunConfiguration) configuration.getProviderSpecific()).getAccessKeySecret();

        BaseAliyunParams paramObj = BaseAliyunParams.builder()
                .accessKeyId(accessKeyId)
                .action(Constants.ADD_DOMAIN_RECORD)
                .build();
        Map<String, Object> params = mapper.convertValue(paramObj, new TypeReference<>() {});

        AddDomainRecordBody bodyObj = AddDomainRecordBody.builder()
                .domainName(configuration.getDomain())
                .rr(configuration.getSubDomain())
                .value(currentIP)
                .build();
        Map<String, Object> body = mapper.convertValue(bodyObj, new TypeReference<>() {});

        fillInSignature(params, body, accessKeySecret, RequestType.POST);
        ApiResponse response = aliyunApi.update(Map.of("Content-Type", "application/x-www-form-urlencoded"), params, body);
        if (log.isDebugEnabled()) {
            log.debug("DNS record creation requested, response: {}", response);
        }

        if (!Objects.equals(response.httpStatus(), 200)) {
            throw ApplicationException.of("Error occurred creating DNS record for Alidns, message: " + response.message());
        }
    }

    private void update(String id) {
        String currentIP = currentIP();
        String accessKeyId = ((AliyunConfiguration) configuration.getProviderSpecific()).getAccessKeyId();
        String accessKeySecret = ((AliyunConfiguration) configuration.getProviderSpecific()).getAccessKeySecret();

        BaseAliyunParams paramObj = BaseAliyunParams.builder()
                .accessKeyId(accessKeyId)
                .action(Constants.UPDATE_DOMAIN_RECORD)
                .build();
        Map<String, Object> params = mapper.convertValue(paramObj, new TypeReference<>() {});

        UpdateDomainRecordBody bodyObj = UpdateDomainRecordBody.builder()
                .recordId(id)
                .rr(configuration.getSubDomain())
                .value(currentIP)
                .build();
        Map<String, Object> body = mapper.convertValue(bodyObj, new TypeReference<>() {});

        fillInSignature(params, body, accessKeySecret, RequestType.POST);
        ApiResponse response = aliyunApi.update(Map.of("Content-Type", "application/x-www-form-urlencoded"), params, body);
        if (log.isDebugEnabled()) {
            log.debug("DNS update queried, response: {}", response);
        }

        if (!Objects.equals(response.httpStatus(), 200)) {
            throw ApplicationException.of("Error occurred updating DNS record for Alidns, message: " + response.message() + ", record id: " + id);
        }
    }

    @Override
    public void handle() throws Exception {
        AliyunRecordGetResponse response = currentRecords();
        if (log.isDebugEnabled()) {
            log.debug("Current DNS record queried, result: {}", response);
        }

        if (CollectionUtils.isEmpty(response.domainRecords())) {
            log.info("Current DNS record for this subdomain not found, trying to create new one.");
            create();
        } else {
            List<AliyunRecordGetResponse.AliyunDNSRecord> records = response.domainRecords();
            if (records.size() != 1) {
                log.error("Currently cannot handle DNS records more than 1 for your server, actually got {} records.", records.size());
                return;
            }

            String id = records.get(0).recordId();
            log.info("Found 1 DNS record, id: {}, trying to update to current IP address.", id);
            update(id);
        }
    }
}
