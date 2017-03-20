package team2.com.easyaspi.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by DLau on 2017-03-14.
 */

public class ProfileDaoImplementation implements IProfileDao {
    public SQLiteDatabase db;
    public static final String PROFILE_TABLE_NAME = "profile";
    public static final String PROFILE_ID = "id";
    public static final String PROFILE_NAME = "name";

    @Override
    public void add(ProfileModel profile) {
        ContentValues values = new ContentValues();
        values.put(PROFILE_ID,profile.getId());
        values.put(PROFILE_NAME,profile.getName());
        db.insert(PROFILE_TABLE_NAME,null,values);
    }

    @Override
    public void update(ProfileModel profile) {

    }

    @Override
    public void delete(ProfileModel profile) {

    }

    @Override
    public List<ProfileModel> listAllProfile() {
        return null;
    }
}
