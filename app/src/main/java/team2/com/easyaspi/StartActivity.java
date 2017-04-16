/*
*   Name: StartActivity.java
*   Description: Start Activity Class that displays when App is launched
*   Last Modified: 2017, March 04
*   Last Modified By: Taera Kwon
 */


package team2.com.easyaspi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        List<Profile> profileList = GetProfileList();

        if(profileList == null) {
            CreateProfileList();
            profileList = GetProfileList();
        }

        for (Profile p: profileList) {
            System.out.println(p.toString());
        }
    }

    public void CreateProfileList() {
        System.out.println("Create profile list");
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Profile 1", "This is profile 1", "cow"));
        profileList.add(new Profile("Profile 2", "This is profile 2", "penguin"));
        profileList.add(new Profile("Profile 3", "This is profile 3", "pig"));

        // Create a shared preference
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // Use this to save
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(profileList);
        prefsEditor.putString("ProfileList", json);
        prefsEditor.apply();
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
            System.out.println("SA getProfileList Error: " + exception.getMessage());
        }
        return profileList;
    }

    // When Back Button is pressed
    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit the application?")
                .setMessage("Are you sure you want to close the application?")
                // if yes
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        moveTaskToBack(true); // Completely closes application
                    }
                })
                // If no
                .setNegativeButton("No", null)
                .show();
    }

    // When Login Button  is pressed
    public void onLoginPressed(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // When Create Profile Button  is pressed
    public void onCreateProfilePressed(View view){
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
    }

    // When Login Profile Button  is pressed
    public void onLoginProfilePressed(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
