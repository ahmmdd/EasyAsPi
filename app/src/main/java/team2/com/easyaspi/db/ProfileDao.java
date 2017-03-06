package team2.com.easyaspi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by DLau on 2017-03-05.
 */

public class ProfileDao extends ProfileDBDao {

    public ProfileDao(Context context)
    {
        super(context);
    }

    /**
     *
     * @param profile gets obj from profile
     * @return insert value into database
     */
    public long add(ProfileModel profile)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PROFILE_ID,profile.getId());
        values.put(DatabaseHelper.PROFILE_NAME,profile.getName());

        return db.insert(DatabaseHelper.PROFILE_TABLE_NAME,null,values);
    }

    /**
     *
     * @return list of profiles
     */
    public ArrayList<ProfileModel> getProfile(){
        ArrayList<ProfileModel> profiles = new ArrayList<ProfileModel>();
        Cursor cursor = db.query(DatabaseHelper.PROFILE_TABLE_NAME, new String[]
                {DatabaseHelper.PROFILE_ID,DatabaseHelper.PROFILE_NAME},null,null,null,null,null);

        while(cursor.moveToNext()){
            ProfileModel profile = new ProfileModel();
            profile.setId(cursor.getInt(0));
            profile.setName(cursor.getString(1));
            profiles.add(profile);
        }
        return profiles;
    }


}
