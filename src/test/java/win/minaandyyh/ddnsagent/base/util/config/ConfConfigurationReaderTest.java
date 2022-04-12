package win.minaandyyh.ddnsagent.base.util.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;
import win.minaandyyh.ddnsagent.Main;
import win.minaandyyh.ddnsagent.base.constant.Constants;
import win.minaandyyh.ddnsagent.base.model.AliyunConfiguration;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;
import win.minaandyyh.ddnsagent.base.model.DNSProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Unit tests for {@link ConfConfigurationReader}
 *
 * @author masteryyh
 */
@ContextConfiguration(classes = Main.class)
public class ConfConfigurationReaderTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private ConfConfigurationReader reader;

    private final List<ApplicationConfiguration> testCases = new LinkedList<>();

    @BeforeTest
    public void initializeProperty() {
        System.setProperty(Constants.DDNS_TEST, "true");
    }

    @BeforeClass
    public void initializeFiles() throws IOException {
        StringBuilder builder1 = new StringBuilder()
                .append("ddns.domain=minaandyyh.win").append("\n")
                .append("ddns.sub-domain=login").append("\n")
                .append("ddns.interval-unit=HOURS").append("\n")
                .append("ddns.interval=4").append("\n")
                .append("ddns.provider=ALIYUN").append("\n")
                .append("ddns.provider-specific.aliyun.access-key-id=aBcD123EfGUAs9dnB2fFg1DD").append("\n")
                .append("ddns.provider-specific.aliyun.access-key-secret=aBcD123EfGUAs9dnB2fFg1DDsh38fh").append("\n")
                .append("ddns.provider-specific.aliyun.record-id=9999998").append("\n");
        Files.writeString(Paths.get("config.conf"), builder1);

        ApplicationConfiguration configuration1 = new ApplicationConfiguration();
        configuration1.setDomain("minaandyyh.win");
        configuration1.setSubDomain("login");
        configuration1.setIntervalUnit(TimeUnit.HOURS);
        configuration1.setInterval(4L);
        configuration1.setProvider(DNSProvider.ALIYUN);
        AliyunConfiguration aliyun1 = new AliyunConfiguration();
        aliyun1.setAccessKeyId("aBcD123EfGUAs9dnB2fFg1DD");
        aliyun1.setAccessKeySecret("aBcD123EfGUAs9dnB2fFg1DDsh38fh");
        aliyun1.setRecordId("9999998");
        configuration1.setProviderSpecific(aliyun1);
        testCases.add(configuration1);
    }

    @AfterClass
    public void deleteFiles() throws IOException {
        Path file1 = Path.of("config.conf");
        if (Files.exists(file1)) {
            Files.delete(file1);
        }
    }

    @DataProvider
    public Object[][] testReadTestCases() {
        return new Object[][] {
                { "config.conf", testCases.get(0) }
        };
    }

    @Test(dataProvider = "testReadTestCases", suiteName = "conf-reader", groups = "basic")
    public void testRead(String path, ApplicationConfiguration configuration) throws Exception {
        ApplicationConfiguration actual = reader.read(path).orElseThrow();
        Assert.assertEquals(actual, configuration);
    }
}
