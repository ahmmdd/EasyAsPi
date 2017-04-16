package team2.com.easyaspi.apiPackage;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import team2.com.easyaspi.R;
import team2.com.easyaspi.adaptersPackage.NotificationAdapter;

/**
 * Created by DLau on 2017-04-12.
 * File name: NotificationFragment.java
 */

public class NotificationFragment extends Fragment{
    private ListView notificationList;
    private View view;

    //default constructor
    public NotificationFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNotifications();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notification_layout,container,false);
        TextView header = (TextView)view.findViewById(R.id.header_notification);
        header.setText("Application Notifications");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

    //Method that will transfer all the JSON obj that was pulled from ApiCallTasks
    private void getNotifications(){
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "json"));

        ApiCallTasks.getNotifications(getActivity().getApplicationContext(),"api/notification",headers.toArray(new Header[headers.size()]),null, new JsonHttpResponseHandler() {

            //Overriding onSuccess method that will get the JSON obj and display onto the ListView. Execute when ApiCallTasks returns headers
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                ArrayList<NotificationBean> notificationArray = new ArrayList<NotificationBean>();
                NotificationAdapter adapter = new NotificationAdapter(getActivity().getApplicationContext(),notificationArray);

                for(int i=0;i<response.length();i++) {
                    try{
                        adapter.add(new NotificationBean(response.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //find ListView list_notification
                notificationList = (ListView)view.findViewById(R.id.list_notifications);
                notificationList.setAdapter(adapter);
            }

            //Overriding onFailure method when ApiCallTasks cannot return headers
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("Failure", "OnFailure" + errorResponse);
                Log.e("Failure", "OnFailure status code" + statusCode);

            }

        });
    }
}
