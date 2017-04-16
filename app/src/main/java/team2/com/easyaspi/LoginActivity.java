package team2.com.easyaspi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Selina on 2017-03-19.
 */

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        List<Profile> profileList = GetProfileList();
        CreateButtons(profileList);
    }

    public List<Profile> GetProfileList() {
        List<Profile> profileList = null;

        try{
            // To retrieve current profile
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            Gson gson = new Gson();
            String json = sharedPref.getString("ProfileList", "");//"ProfileList";
            Type listType = new TypeToken<List<Profile>>(){}.getType();
            profileList = (List<Profile>) gson.fromJson(json, listType);

        }
        catch(Exception exception){
            System.out.println("LA getProfileList Error: " + exception.getMessage());
        }
        return profileList;
    }

    public void CreateButtons(List<Profile> profileList) {

        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_login);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.profile_button_width), LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(0, 30, 0, 0);

        for (Profile p: profileList) {
            Button button = new Button(this);
            button.setId(Integer.parseInt(p.getId()));
            button.setText(p.getName());

            button.setLayoutParams(layoutParams);
            button.setOnClickListener(getOnClickDoSomething(button, p));
            ll.addView(button);
        }
    }

    View.OnClickListener getOnClickDoSomething(final Button button, final Profile profile)  {
        return new View.OnClickListener() {
            public void onClick(View v) {

                //Set current profile
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor prefsEditor = sharedPref.edit();
                Gson gson = new Gson();
                String json = gson.toJson(profile);
                prefsEditor.putString("SelectedProfile", json);
                prefsEditor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
    }
}
