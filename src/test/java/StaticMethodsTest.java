import com.matheuscordeiro.StaticUtils;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mockStatic;

public class StaticMethodsTest {
    @Test
    void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully() {
        assertThat(StaticUtils.name()).isEqualTo("Cordeiro");

        try (MockedStatic<StaticUtils> utilities = mockStatic(StaticUtils.class)) {
            utilities.when(StaticUtils::name).thenReturn("Matheus");
            assertThat(StaticUtils.name()).isEqualTo("Matheus");
        }

        assertThat(StaticUtils.name()).isEqualTo("Cordeiro");
    }

    @Test
    void givenStaticMethodWithArgs_whenMocked_thenReturnsMockSuccessfully() {
        assertThat(StaticUtils.range(2, 6)).containsExactly(2, 3, 4, 5);

        try (MockedStatic<StaticUtils> utilities = mockStatic(StaticUtils.class)) {
            utilities.when(() -> StaticUtils.range(2, 6))
                    .thenReturn(Arrays.asList(10, 11, 12));

            assertThat(StaticUtils.range(2, 6)).containsExactly(10, 11, 12);
        }

        assertThat(StaticUtils.range(2, 6)).containsExactly(2, 3, 4, 5);
    }
}
