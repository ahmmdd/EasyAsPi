package team2.com.easyaspi;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Type;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Selina on 2017-04-16.
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoSelectProfile {
    @Mock
    LoginActivity loginActivity = mock(LoginActivity.class);

    @Test
    public void testLogin() {

        Button button = new Button(loginActivity);
        Profile profile = new Profile("Test", "MockitoSelectProfile", "cow");
        loginActivity.getOnClickDoSomething(button ,profile);
        verify(loginActivity).getOnClickDoSomething(button, profile);
        verifyNoMoreInteractions(loginActivity);

    }

}
