import com.matheuscordeiro.MyList;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class VerifyTest {
    @Test
    @DisplayName("Verify simple invocation on mock")
    public void test() {
        var mockedList = mock(MyList.class);
        mockedList.size();
        verify(mockedList).size();
    }

    @Test
    @DisplayName("Verify number of interactions with mock")
    public void test2() {
        var mockedList = mock(MyList.class);
        mockedList.size();
        verify(mockedList, times(1)).size();
    }

    @Test
    @DisplayName("Verify no interaction with the whole mock occurred")
    public void test3() {
        var mockedList = mock(MyList.class);
        verifyZeroInteractions(mockedList);
    }

    @Test
    @DisplayName("Verify no interaction with a specific method occurred")
    public void test4() {
        var mockedList = mock(MyList.class);
        verify(mockedList, times(0)).size();
    }

    @Test
    @DisplayName("Verify there are no unexpected interactions — this should fail")
    public void test5() {
        var mockedList = mock(MyList.class);
        mockedList.size();
        mockedList.clear();
        verify(mockedList).size();
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    @DisplayName("Verify order of interactions")
    public void test6() {
        var mockedList = mock(MyList.class);
        mockedList.size();
        mockedList.add("a parameter");
        mockedList.clear();

        InOrder inOrder = inOrder(mockedList);
        inOrder.verify(mockedList).size();
        inOrder.verify(mockedList).add("a parameter");
        inOrder.verify(mockedList).clear();
    }

    @Test
    @DisplayName("Verify an interaction has not occurred")
    public void test7() {
        var mockedList = mock(MyList.class);
        mockedList.size();
        verify(mockedList, never()).clear();
    }

    @Test
    @DisplayName("Verify an interaction has occurred at least a certain number of times")
    public void test8() {
        var mockedList = mock(MyList.class);
        mockedList.clear();
        mockedList.clear();
        mockedList.clear();

        verify(mockedList, atLeast(1)).clear();
        verify(mockedList, atMost(10)).clear();
    }

    @Test
    @DisplayName("Verify interaction with exact argument")
    public void test9() {
        var mockedList = mock(MyList.class);
        mockedList.add("test");
        verify(mockedList).add("test");
    }

    @Test
    @DisplayName("Verify interaction with flexible/any argument")
    public void test10() {
        var mockedList = mock(MyList.class);
        mockedList.add("test");
        verify(mockedList).add(anyString());
    }

    @Test
    @DisplayName("Verify interaction using argument capture")
    public void test11() {
        var mockedList = mock(MyList.class);
        mockedList.addAll(Arrays.asList("someElement"));
        var argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mockedList).addAll((Collection<? extends String>) argumentCaptor.capture());
        List<String> capturedArgument = argumentCaptor.<List<String>> getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }
}
