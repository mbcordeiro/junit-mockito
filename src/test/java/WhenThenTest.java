import com.matheuscordeiro.MyList;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WhenThenTest {
    @Test
    @DisplayName("Configure simple return behavior for mock")
    public void test1() {
        var listMock = mock(MyList.class);
        when(listMock.add(anyString())).thenReturn(false);

        boolean added = listMock.add(randomAlphabetic(6));
        assertThat(added, is(false));
    }

    @Test
    @DisplayName("Configure return behavior for mock in an alternative way")
    public void test2() {
        var listMock = mock(MyList.class);
        doReturn(false).when(listMock).add(anyString());

        boolean added = listMock.add(randomAlphabetic(6));
        assertThat(added, is(false));
    }

    @Test(expected = IllegalStateException.class)
    @DisplayName("Configure mock to throw an exception on a method call")
    public void givenMethodIsConfiguredToThrowException_whenCallingMethod_thenExceptionIsThrown() {
        var listMock = mock(MyList.class);
        when(listMock.add(anyString())).thenThrow(IllegalStateException.class);
        listMock.add(randomAlphabetic(6));
    }

    @Test
    @DisplayName("Configure the behavior of a method with void return type — to throw an exception")
    public void test3() {
        var listMock = mock(MyList.class);
        doThrow(NullPointerException.class).when(listMock).clear();
        listMock.clear();
    }

    @Test
    @DisplayName("Configure the behavior of multiple calls")
    public void test4   () {
        var listMock = mock(MyList.class);
        when(listMock.add(anyString()))
                .thenReturn(false)
                .thenThrow(IllegalStateException.class);
        listMock.add(randomAlphabetic(6));
        listMock.add(randomAlphabetic(6));
    }

    @Test
    @DisplayName("Configure the behavior of a spy")
    public void test5() {
        var instance = new MyList();
        var spy = spy(instance);
        doThrow(NullPointerException.class).when(spy).size();
        spy.size();
    }

    @Test
    @DisplayName("Configure method to call the real, underlying method on a mock")
    public void test6() {
        var listMock = mock(MyList.class);
        when(listMock.size()).thenCallRealMethod();
        assertThat(listMock.size(), equalTo(1));
    }

    @Test
    @DisplayName("Configure mock method call with custom Answer")
    public void test7() {
        var listMock = mock(MyList.class);
        doAnswer(invocation -> "Always the same").when(listMock).get(anyInt());
        String element = listMock.get(1);
        assertThat(element, is(equalTo("Always the same")));
    }

    private String randomAlphabetic(int i) {
        return "";
    }
}
