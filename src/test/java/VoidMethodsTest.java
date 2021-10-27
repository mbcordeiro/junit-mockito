import com.matheuscordeiro.MyList;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class VoidMethodsTest {
    @Test
    public void whenAddCalledVerified() {
        var myList = mock(MyList.class);
        doNothing().when(myList).add(isA(Integer.class), isA(String.class));
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
    }

    @Test(expected = Exception.class)
    public void givenNull_AddThrows() {
        var myList = mock(MyList.class);
        doThrow().when(myList).add(isA(Integer.class), isNull());

        myList.add(0, null);
    }

    @Test
    public void whenAddCalledValueCaptured() {
        var myList = mock(MyList.class);
        var valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(myList).add(any(Integer.class), valueCapture.capture());
        myList.add(0, "captured");

        assertEquals("captured", valueCapture.getValue());
    }

    @Test
    public void whenAddCalledAnswered() {
        var myList = mock(MyList.class);
        doAnswer(invocation -> {
            var arg0 = invocation.getArgument(0);
            var arg1 = invocation.getArgument(1);

            assertEquals(3, arg0);
            assertEquals("answer me", arg1);
            return null;
        }).when(myList).add(any(Integer.class), any(String.class));
        myList.add(3, "answer me");
    }

    @Test
    public void whenAddCalledRealMethodCalled() {
        var myList = mock(MyList.class);
        doCallRealMethod().when(myList).add(any(Integer.class), any(String.class));
        myList.add(1, "real");

        verify(myList, times(1)).add(1, "real");
    }
}
