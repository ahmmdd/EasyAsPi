package team2.com.easyaspi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Selina on 2017-03-20.
 */

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Context context = view.getContext();

        // Set up a fall back profile
        Profile profile = new Profile("0", "Profile 0", "This is profile 0", "logo");

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

        // Set values profile values to the current profile
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
                // Call the delete profile method
                //DeleteProfile();
            }
        });

        return view;
    }

    // Edit profile
    public void EditProfile(View view){

        Fragment fragment = null; // Declare null fragment object
        Class fragmentClass = EditProfileFragment.class;

        try {
            // Create new instance
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();//getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
