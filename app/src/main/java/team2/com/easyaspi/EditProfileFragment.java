package team2.com.easyaspi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

/**
 * Created by Selina on 2017-03-24.
 */

public class EditProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        Context context = view.getContext();

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

        etProfileName.setText(profile.getName());
        etProfileDetails.setText(profile.getDetails());
        //spinnerImageName.selec

        return view;
    }


}
