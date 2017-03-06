package team2.com.easyaspi.db;

/**
 * Created by DLau on 2017-03-05.
 */

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class ProfileDBDao {
    protected SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public ProfileDBDao(Context context){
        this.mContext = context;
        dbHelper = DatabaseHelper.getHelper(mContext);
        open();
    }
    public void open() throws SQLException {
        if (dbHelper == null){
            dbHelper = DatabaseHelper.getHelper(mContext);
            db = dbHelper.getWritableDatabase();
        }
    }
}
