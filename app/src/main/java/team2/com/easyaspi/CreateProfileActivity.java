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
                /*// Verify that the user wants to create a profile
                new AlertDialog.Builder(CreateProfileActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Create Profile")
                        .setMessage("Are you sure you want to create this profile?")
                        // if yes
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Call the edit profile method
                                CreateProfile();
                            }
                        })
                        // If no
                        .setNegativeButton("No", null)
                        .show();*/
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

    public void CreateProfile() { //View view) {

        try{
            // Set up a fall back profile
            Profile profile = new Profile("0", "Profile 0", "This is profile 0", "logo");

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
        }
        catch(Exception exception){
            System.out.println("CreateProfileFragment Error: " + exception.getMessage());
        }

        // Go to the main activity
        Intent intent = new Intent(CreateProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
