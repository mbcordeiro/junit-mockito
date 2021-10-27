import com.matheuscordeiro.MyDictionary;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {
    @Mock
    List mockedList;

    @Spy
    List<String> spiedList = new ArrayList<String>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        spyDic = Mockito.spy(new MyDictionary(wordMap));
    }

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Mock
    Map<String, String> wordMap;

    @Spy
    MyDictionary spyDic = new MyDictionary();

    @Captor
    ArgumentCaptor argCaptor;

    @InjectMocks
    MyDictionary dic = new MyDictionary();

    MyDictionary spyDic2;

    @Mock
    List<String> mockedListString;

    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        var mockList = Mockito.mock(ArrayList.class);

        mockList.add("one");
        verify(mockList).add("one");
        assertEquals(0, mockList.size());

        when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");
        verify(mockedList).add("one");
        assertEquals(0, mockedList.size());

        when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }

    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {
        var spyList = spy(new ArrayList<String>());

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());

        doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");

        verify(spiedList).add("one");
        verify(spiedList).add("two");

        assertEquals(2, spiedList.size());

        doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());
    }

    @Test
    public void whenNotUseCaptorAnnotation_thenCorrect() {
        var mockList = Mockito.mock(List.class);
        var arg = ArgumentCaptor.forClass(String.class);

        mockList.add("one");
        verify(mockList).add(arg.capture());

        assertEquals("one", arg.getValue());
    }

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        mockedList.add("one");
        verify(mockedList).add(argCaptor.capture());

        assertEquals("one", argCaptor.getValue());
    }

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", dic.getMeaning("aWord"));
    }

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect2() {
        when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", spyDic2.getMeaning("aWord"));
    }

    @Test(expected = NullPointerException.class)
    public void whenMockitoAnnotationsUninitialized_thenNPEThrown() {
        when(mockedListString.size()).thenReturn(1);
    }
}
