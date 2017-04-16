package team2.com.easyaspi.apiPackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DLau on 2017-04-10.
 * File name: NotificationBean.java
 * File description: Model that will hold data for notification
 */

public class NotificationBean {
    //variables
    private String id;
    private String subject;
    private String notification;

    //constrcutor
    public NotificationBean(JSONObject object) {
        try {
            this.id = object.getString("_id");
            this.subject = object.getString("subject");
            this.notification = object.getString("notification");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
