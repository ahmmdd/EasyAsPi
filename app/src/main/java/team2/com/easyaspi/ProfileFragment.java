package team2.com.easyaspi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Selina on 2017-03-20.
 */

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        final Context context = view.getContext();

        // Change the toolbar title
        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("View Profile");
        }

        // Set up a fall back profile
        Profile profile = null;

        // Get components
        TextView tvProfileName = (TextView) view.findViewById(R.id.profile_view_tvName);
        TextView tvProfileDetails = (TextView) view.findViewById(R.id.profile_view_tvDetails);
        ImageView ivProfileImage = (ImageView) view.findViewById(R.id.profile_view_imageView);
        Button btnEdit = (Button) view.findViewById(R.id.profile_view_btnEdit);
        Button btnDelete = (Button) view.findViewById(R.id.profile_view_btnDelete);

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

        // Set profile values to the profile view components
        tvProfileName.setText(profile.getName());
        tvProfileDetails.setText(profile.getDetails());
        ivProfileImage.setImageResource(profile.setImage(ivProfileImage, context));

        // Create listeners for the buttons
        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Call the edit profile method
                EditProfile(v);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Verify that the user wishes to delete the profile
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Profile")
                        .setMessage("Are you sure you want to delete this profile?")
                        // if yes
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Call the delete profile method
                                DeleteProfile();

                                // Return to the start activity
                                Intent intent = new Intent(context, StartActivity.class);
                                startActivity(intent);
                            }
                        })
                        // If no
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.theTitle);
    }

    // Edit profile
    public void EditProfile(View view){

        Fragment fragment = null; // Declare null fragment object
        Class fragmentClass = EditProfileFragment.class;

        try {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Profile");

            // Create new instance
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();//getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment)
                    .addToBackStack(null) // Puts fragment into stack so back button goes back to previous state
                    .commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void DeleteProfile() {
        // Get profile list
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        Gson gson = new Gson();
        String jsonGetList = sharedPref.getString("ProfileList", "");
        Type listType = new TypeToken<List<Profile>>(){}.getType();
        List<Profile> profileList = (List<Profile>) gson.fromJson(jsonGetList, listType);

        // Get current profile
        String jsonGetProfile = sharedPref.getString("SelectedProfile", "");
        Profile profile = gson.fromJson(jsonGetProfile, Profile.class);

        // Find current profile in the list
        int count = 0, position = -1;
        for (Profile p: profileList) {
            if(p.getId().equals(profile.getId()) && p.getName().equals(profile.getName()) && p.getDetails().equals(profile.getDetails()) && p.getImageName().equals(profile.getImageName())) {
                position = count;
            }
            count++;
        }

        // Remove the current profile from the list
        profileList.remove(position);

        // Update the list of profiles
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        String jsonUpdateList = gson.toJson(profileList);
        prefsEditor.putString("ProfileList", jsonUpdateList);
        prefsEditor.apply();
    }
}
