import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SpyTest {
    @BeforeAll
    public void init() {
        initMocks(this);
    }

    @Spy
    List<String> spyList = new ArrayList<String>();

    @Test
    public void whenSpyingOnList_thenCorrect() {
        var list = new ArrayList<String>();
        var spyList = spy(list);

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }

    @Test
    public void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }

    @Test
    public void whenStubASpy_thenStubbed() {
        var list = new ArrayList<String>();
        var spyList = spy(list);

        assertEquals(0, spyList.size());

        doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    @Test
    public void whenCreateMock_thenCreated() {
        var mockedList = mock(ArrayList.class);

        mockedList.add("one");
        verify(mockedList).add("one");

        assertEquals(0, mockedList.size());
    }

    @Test
    public void whenCreateSpy_thenCreate() {
        var spyList = spy(new ArrayList());

        spyList.add("one");
        verify(spyList).add("one");

        assertEquals(1, spyList.size());
    }

    @Test
    public void test() {
        var list = new ArrayList<String>();
        doReturn(100).when(list).size();

        assertEquals(100, list.size());
    }

    @Test
    public void test2() {
        final var spyList = spy(new ArrayList<String>());
        doReturn(100).when(spyList).size();

        assertEquals(100, spyList.size());
    }
}
