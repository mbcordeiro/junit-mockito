import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(JUnit4.class)
public class BeforeAllTest {
    private static Logger LOG = LoggerFactory.getLogger(JunitTest.class);

    @BeforeAll
    public static void setup() {
        LOG.info("startup - creating DB connection");
    }

    @AfterAll
    public static void tearDown() {
        LOG.info("closing DB connection");
    }

    @Test
    public void simpleTest() {
        LOG.info("simple test");
    }

    @Test
    public void anotherSimpleTest() {
        LOG.info("another simple test");
    }

}
