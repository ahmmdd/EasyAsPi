package team2.com.easyaspi.apiPackage;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import cz.msebera.android.httpclient.Header;



/**
 * Created by DLau on 2017-04-09.
 */

public class ApiCallTasks {

    private static final String TAG = "FEEDBACK";
    private Context context;


    AsyncHttpClient client;
    RequestParams requestParams;

    String BASE_URL ="https://easyaspi-api.herokuapp.com/api/feedback";
    String jsonResponse;

    public ApiCallTasks(Context context) {
        client = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.context = context;
    }

    public void executeApiCall(String query) {
        requestParams.put("get",query);

        client.get(BASE_URL,requestParams, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                super.onSuccess(statusCode,headers,response);
                jsonResponse = response.toString();
                //apiListener.taskCompleted(jsonResponse);
                Log.i(TAG,"Successfully called Api: " + jsonResponse);
            }
        });
    }
}
