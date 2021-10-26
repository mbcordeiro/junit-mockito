import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionTest {
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        var exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });

        var expectedMessage = "For input string";
        var actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenDerivedExceptionThrown_thenAssertionSucceds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
