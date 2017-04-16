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
 */

public class ApiCallTasks {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String BASE_URL_NOTIFICATION ="https://easyaspi-api.herokuapp.com/";


    public static void getNotifications(Context context, String url, Header[] headers, RequestParams requestParams, JsonHttpResponseHandler responseHandler) {

        client.get(context,getAbsoluteUrl(url),headers,requestParams,responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL_NOTIFICATION + relativeUrl;
    }
}
