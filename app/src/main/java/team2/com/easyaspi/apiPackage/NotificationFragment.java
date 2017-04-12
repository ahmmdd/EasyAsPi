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

    private void getNotifications(){
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "json"));

        ApiCallTasks.getNotifications(getActivity().getApplicationContext(),"api/notification",headers.toArray(new Header[headers.size()]),null, new JsonHttpResponseHandler() {

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
                notificationList = (ListView)view.findViewById(R.id.list_notifications);
                notificationList.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("Failure", "OnFailure" + errorResponse);
                Log.e("Failure", "OnFailure status code" + statusCode);

            }

        });
    }
}
