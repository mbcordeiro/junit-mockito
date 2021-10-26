import com.matheuscordeiro.MyBean;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class JunitTest {
    private static Logger log = LoggerFactory.getLogger(JunitTest.class);

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }

    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        log.info("Success");
    }

    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }

    @Test
    void lambdaExpressions() {
        assertTrue(Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum() > 5, () -> "Sum should be greater than 5");
    }
    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 0),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 1)
        );
    }

    @Test
    void trueAssumption() {
        assumeTrue(5 > 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void falseAssumption() {
        assumeFalse(5 < 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void assumptionThat() {
        var someString = "Just a string";
        assumingThat(
                someString.equals("Just a string"),
                () -> assertEquals(2 + 2, 4)
        );
    }

    @Test
    void shouldThrowException() {
        var exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals(exception.getMessage(), "Not supported");
    }

    @Test
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }

    @Test
    void givenEvenNumber_whenCheckingIsNumberEven_thenTrue() {
        var bean = new MyBean();
        var result = bean.isNumberEven(8);
        Assertions.assertTrue(result);
    }

    @Test
    void givenOddNumber_whenCheckingIsNumberEven_thenFalse() {
        var bean = new MyBean();
        var result = bean.isNumberEven(3);
        Assertions.assertFalse(result);
    }

    @Test
    void givenLowerThanTenNumber_whenCheckingIsNumberEven_thenResultUnderTenMillis() {
        var bean = new MyBean();
        Assertions.assertTimeout(Duration.ofMillis(10), () -> bean.isNumberEven(3));
    }

    @Test
    void givenNull_whenCheckingIsNumberEven_thenNullPointerException() {
        var bean = new MyBean();
        Assertions.assertThrows(NullPointerException.class, () -> bean.isNumberEven(null));
    }
}
