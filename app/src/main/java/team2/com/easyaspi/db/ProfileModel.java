package team2.com.easyaspi.db;

/**
 * Created by Dominic Lau on 2017-03-05.
 */

public class ProfileModel {

    private int id;
    private String name;

    public ProfileModel(){}

    public ProfileModel(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
