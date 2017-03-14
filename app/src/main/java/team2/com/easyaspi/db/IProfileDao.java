package team2.com.easyaspi.db;

import java.util.List;

/**
 * Created by DLau on 2017-03-14.
 */

public interface IProfileDao {
    public void add(ProfileModel profile);
    public void update(ProfileModel profile);
    public void delete (ProfileModel profile);
    public List<ProfileModel> listAllProfile();
}
