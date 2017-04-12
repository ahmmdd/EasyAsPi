package team2.com.easyaspi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Selina on 2017-03-24.
 */

public class EditProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        final Context context = view.getContext();

        // Set up a fall back profile
        Profile profile = new Profile("0", "Profile 0", "This is profile 0", "logo");

        // Get components
        EditText etProfileName = (EditText) view.findViewById(R.id.profile_edit_etName);
        EditText etProfileDetails = (EditText) view.findViewById(R.id.profile_edit_etDetails);
        Spinner spinnerImageName = (Spinner) view.findViewById(R.id.profile_edit_spinner_image);
        Button btnUpdate = (Button) view.findViewById(R.id.profile_edit_btnUpdate);
        Button btnCancel = (Button) view.findViewById(R.id.profile_edit_btnCancel);

        try{
            // To retrieve current profile
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
            Gson gson = new Gson();
            String json = sharedPref.getString("SelectedProfile", "");
            profile = gson.fromJson(json, Profile.class);
        }
        catch(Exception exception){
            System.out.println("ProfileFragment Error: " + exception.getMessage());
        }

        // Set profile values in the components
        etProfileName.setText(profile.getName());
        etProfileDetails.setText(profile.getDetails());
        spinnerImageName.setSelection(GetImagePosition(spinnerImageName, profile));

        // Create listeners for the buttons
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Verify that the user wants to make changes
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Update Profile")
                        .setMessage("Are you sure you want to update this profile?")
                        // if yes
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Call the edit profile method
                                UpdateProfile(getView());
                            }
                        })
                        // If no
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Verify that the user wants to cancel the update
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Cancel")
                        .setMessage("Are you sure you want to cancel update?")
                        // if yes
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Return to the view profile fragment
                                ReturnToViewProfile();
                            }
                        })
                        // If no
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }

    public int GetImagePosition(Spinner theSpinner, Profile profile) {
        Adapter adapter = theSpinner.getAdapter();
        int n = adapter.getCount();
        int itemPosition = 0;

        for (int i = 0; i < n; i++) {
            if(adapter.getItem(i).toString().equals(profile.getImageName())) {
                // Set the return value
                itemPosition = i;
            }
        }

        return itemPosition;
    }

    public void ReturnToViewProfile() {

        Fragment fragment = null; // Declare null fragment object
        Class fragmentClass = ProfileFragment.class;

        try {

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("View Profile");
            super.getActivity().onBackPressed();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void UpdateProfile(View view) {

        try{
            // Set up a fall back profile
            Profile profile = new Profile("0", "Profile 0", "This is profile 0", "logo");

            // Get components
            EditText etProfileName = (EditText) view.findViewById(R.id.profile_edit_etName);
            EditText etProfileDetails = (EditText) view.findViewById(R.id.profile_edit_etDetails);
            Spinner spinnerImageName = (Spinner) view.findViewById(R.id.profile_edit_spinner_image);

            // To retrieve current profile
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
            Gson gson = new Gson();
            String json = sharedPref.getString("SelectedProfile", "");
            profile = gson.fromJson(json, Profile.class);

            // Update current profile information
            profile.setName(etProfileName.getText().toString());
            profile.setDetails(etProfileDetails.getText().toString());
            profile.setImageName(spinnerImageName.getSelectedItem().toString());

            // Store the information
            SharedPreferences.Editor prefsEditor = sharedPref.edit();
            json = gson.toJson(profile);
            prefsEditor.putString("SelectedProfile", json);
            prefsEditor.apply();
        }
        catch(Exception exception){
            System.out.println("EditProfileFragment Error: " + exception.getMessage());
        }

        // Return to view profile
        ReturnToViewProfile();
    }
}
