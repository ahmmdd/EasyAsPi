package team2.com.easyaspi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by Taera on 2017-04-14.
 * Description: Mockito Unit Tests
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoNavigationTest {
    @Mock
    MainActivity mainActivity = mock(MainActivity.class);

    // Tests that changeView executes once and verify that there are no more interactions with the mock
    @Test
    public void testFragmentLoader() throws IllegalAccessException, InstantiationException {
        // When
        mainActivity.changeView(R.id.nav_lesson);
        // Then
        verify(mainActivity).changeView(R.id.nav_lesson);
        verifyNoMoreInteractions(mainActivity);
    }
}


