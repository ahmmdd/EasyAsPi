/**
 * Created by DLau on 2017-03-05.
 */
import android.provider.ContactsContract;

import java.util.List;

public interface ProfileDao {
    public List<ProfileModel> getAllProfile();
    public ProfileModel getProfile(int id);
    public void deleteProfile(ProfileModel profileModel);
    public void updateProfile(ProfileModel profileModel);
}
