package team2.com.easyaspi;

/**
 * Created by Selina on 2017-03-19.
 */

public class Profile {
    public final String id;
    public final String name;
    public final String details;

    public Profile(String id, String name, String details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    @Override
    public String toString() {
        return name;
    }
}

