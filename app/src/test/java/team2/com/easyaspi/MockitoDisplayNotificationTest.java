package team2.com.easyaspi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import team2.com.easyaspi.apiPackage.NotificationFragment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by DLau on 2017-04-15.
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoDisplayNotificationTest {
    @Mock
    NotificationFragment m_notificationFragment = mock(NotificationFragment.class);

    @Test
    //Test if notification is displayed
    public void testDisplay() {
        m_notificationFragment.getNotifications();
        verify(m_notificationFragment).getNotifications();
        verifyNoMoreInteractions(m_notificationFragment);
    }

}
