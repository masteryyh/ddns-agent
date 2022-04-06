package win.minaandyyh.ddnsagent.base.util.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;
import win.minaandyyh.ddnsagent.Main;
import win.minaandyyh.ddnsagent.base.constant.Constants;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;
import win.minaandyyh.ddnsagent.base.model.CloudflareConfiguration;
import win.minaandyyh.ddnsagent.base.model.DNSProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Unit tests for {@link JsonConfigurationReader}
 *
 * @author masteryyh
 */
@ContextConfiguration(classes = Main.class)
public class JsonConfigurationReaderTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private JsonConfigurationReader reader;

    @Autowired
    private ObjectMapper mapper;

    private final List<ApplicationConfiguration> testCases = new LinkedList<>();

    @BeforeTest
    public void initializeProperty() {
        System.setProperty(Constants.DDNS_TEST, "true");
    }

    @BeforeClass
    public void initializeFiles() throws IOException {
        // 1. current directory
        ApplicationConfiguration testConfiguration1 = new ApplicationConfiguration();
        testConfiguration1.setDomain("testdomain.com");
        testConfiguration1.setSubDomain("@");
        testConfiguration1.setIntervalUnit(TimeUnit.DAYS);
        testConfiguration1.setInterval(1L);
        testConfiguration1.setProvider(DNSProvider.CLOUDFLARE);

        CloudflareConfiguration cloudflare = new CloudflareConfiguration();
        cloudflare.setAuthKey("test-key");
        cloudflare.setAuthEmail("test123@abc.com");
        testConfiguration1.setProviderSpecific(cloudflare);

        String json = mapper.writeValueAsString(testConfiguration1);
        Files.writeString(Paths.get("config.json"), json);
        testCases.add(testConfiguration1);
    }

    @AfterClass
    public void deleteFiles() throws IOException {
        Path file1 = Path.of("config.json");
        if (Files.exists(file1)) {
            Files.delete(file1);
        }
    }

    @DataProvider
    public Object[][] testReadTestCases() {
        return new Object[][] {
                { "config.json", 0 }
        };
    }

    @Test(dataProvider = "testReadTestCases", suiteName = "json-reader", groups = "basic")
    public void testBasicRead(String path, int index) throws Exception {
        ApplicationConfiguration parsed = reader.read(path).orElseThrow();
        Assert.assertEquals(parsed, testCases.get(index));
    }
}
