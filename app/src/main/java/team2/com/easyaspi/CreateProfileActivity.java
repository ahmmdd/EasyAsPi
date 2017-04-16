package team2.com.easyaspi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Selina on 2017-03-28.
 */

public class CreateProfileActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_profile);

        Button btnCreate = (Button) findViewById(R.id.profile_create_btnCreate);
        Button btnCancel = (Button) findViewById(R.id.profile_create_btnCancel);

        // Create listeners for the buttons
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Call the edit profile method
                CreateProfile();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Verify that the user wants to cancel the update
                new AlertDialog.Builder(CreateProfileActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Cancel")
                        .setMessage("Are you sure you want to cancel creating this profile?")
                        // if yes
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Return to the start activity
                                Intent intent = new Intent(CreateProfileActivity.this, StartActivity.class);
                                startActivity(intent);
                            }
                        })
                        // If no
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    public void CreateProfile() {

        try{
            // Set up a fall back profile
            Profile profile = new Profile("Profile 0", "This is profile 0", "logo");

            // Get components
            EditText etProfileName = (EditText) findViewById(R.id.profile_create_etName);
            EditText etProfileDetails = (EditText) findViewById(R.id.profile_create_etDetails);
            Spinner spinnerImageName = (Spinner) findViewById(R.id.profile_create_spinner_image);

            // Get the profile information and create a profile
            profile.setName(etProfileName.getText().toString());
            profile.setDetails(etProfileDetails.getText().toString());
            profile.setImageName(spinnerImageName.getSelectedItem().toString());

            // Store the information
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(CreateProfileActivity.this);
            SharedPreferences.Editor prefsEditor = sharedPref.edit();
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            prefsEditor.putString("SelectedProfile", json);
            prefsEditor.apply();

            UpdateProfileList(profile);
        }
        catch(Exception exception){
            System.out.println("CreateProfileFragment Error: " + exception.getMessage());
        }

        // Go to the main activity
        Intent intent = new Intent(CreateProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void UpdateProfileList(Profile profile) {
        List<Profile> profileList = null;

        try{
            // To retrieve the list of profiles
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            Gson gson = new Gson();
            String json = sharedPref.getString("ProfileList", "");//"ProfileList";
            Type listType = new TypeToken<List<Profile>>(){}.getType();
            profileList = (List<Profile>) gson.fromJson(json, listType);

            // Add new profile to the list
            profileList.add(profile);

            // Update the profile list
            SharedPreferences.Editor prefsEditor = sharedPref.edit();
            String jsonUpdate = gson.toJson(profileList);
            prefsEditor.putString("ProfileList", jsonUpdate);
            prefsEditor.apply();

        }
        catch(Exception exception){
            System.out.println("CP UpdateProfileList Error: " + exception.getMessage());
        }
    }
}
