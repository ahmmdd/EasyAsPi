package team2.com.easyaspi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;


/**
 * Created by Selina on 2017-03-19.
 */

public class Profile {
    private String id;
    private String name; // Limit the length of the name to 12 characters
    private String details;
    private String imageName;
    private static  int currentID = 1; // This isn't working

    public Profile(String name, String details, String imageName) {
        this.id = "" + currentID;
        this.name = name;
        this.details = details;
        this.imageName = imageName;
        currentID++;
    }

    //Id
    public String getId() { return this.id; }
    //public void setId(String id) { this.id = id; }

    // Name
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Details
    public String getDetails() {
        return this.details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    // Image name
    public String getImageName() { return this.imageName; }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    // Image view
    public int setImage(ImageView image, Context context) {
        Resources res = context.getResources();
        String mDrawableName = this.getImageName();
        int resID = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        return resID;
    }

    @Override
    public String toString() {
        return String.format("ID: " + this.id + ", Name: " + getName() + ", Image name: " + getImageName() + ", Details: " + getDetails());
    }
}

