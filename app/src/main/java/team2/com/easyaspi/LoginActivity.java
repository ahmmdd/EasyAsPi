package team2.com.easyaspi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Selina on 2017-03-19.
 */

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

    }

    // When Login Profile Button  is pressed
    public void onProfilePressed(View view){

        Profile profile;

        // Perform action on click
        switch(view.getId()) {
            case R.id.button_login_profile_1:
                profile = new Profile("1", "Profile 1", "");
                break;
            case R.id.button_login_profile_2:
                profile = new Profile("1", "Profile 2", "");
                break;
            default:
                profile = new Profile("0", "Profile 0", "");
                break;
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("UserName", profile.toString());
        editor.commit();


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
