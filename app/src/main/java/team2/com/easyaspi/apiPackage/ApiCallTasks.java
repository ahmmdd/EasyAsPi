package team2.com.easyaspi.apiPackage;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import cz.msebera.android.httpclient.Header;



/**
 * Created by DLau on 2017-04-09.
 * File name: ApiCallTasks.java
 * File Description: Class that handles all API async tasks
 */

public class ApiCallTasks {

    //Create instance of the AsyncHttpClient
    private static AsyncHttpClient client = new AsyncHttpClient();
    //Declare Variable for URL
    private static String BASE_URL_NOTIFICATION ="https://easyaspi-api.herokuapp.com/";


    //method getNotifications that will receive context, url, header, request parms, responsehandler
    public static void getNotifications(Context context, String url, Header[] headers, RequestParams requestParams, JsonHttpResponseHandler responseHandler) {

    //set httpclient url, header, request params, response handler
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String BASE_URL_NOTIFICATION ="https://easyaspi-api.herokuapp.com/";


    public static void getNotifications(Context context, String url, Header[] headers, RequestParams requestParams, JsonHttpResponseHandler responseHandler) {
        client.get(context,getAbsoluteUrl(url),headers,requestParams,responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL_NOTIFICATION + relativeUrl;
    }
}
