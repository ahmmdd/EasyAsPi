package team2.com.easyaspi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;

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

        Profile profile = new Profile("0", "Profile 0", "This is profile 0", "logo");

        try {
            //Profile profile;

            // Perform action on click
            switch(view.getId()) {
                case R.id.button_login_profile_1:
                    profile = new Profile("1", "Profile 1", "This is profile 1", "cow");
                    break;
                case R.id.button_login_profile_2:
                    profile = new Profile("1", "Profile 2", "This is profile 2", "owl");
                    break;
                default:
                    profile = new Profile("0", "Profile 0", "This is profile 0", "logo");
                    break;
            }
        }
        catch (Exception exception) {
            System.out.println("LoginActivity Error: " + exception.getMessage());
        }

        // Create a shared preference
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // Use this to save
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        prefsEditor.putString("SelectedProfile", json);
        prefsEditor.commit();

        // Other stuff that isn't being used
        //SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("UserName", profile.toString());
        //editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
