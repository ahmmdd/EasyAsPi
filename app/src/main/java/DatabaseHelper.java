import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * File Name: DatabaseHelper.java
 * Created by Dominic Lau on 2017-03-04.
 * Description: This java class will handle the database
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    //Delcaring constant variables
    private static final String DATABASE_NAME = "EasyAsPi.db";
    //Database table profile
    private static final String PROFILE_TABLE_NAME = "profile";
    private static final String PROFILE_NAME = "name";
    //Database table practice
    private static final String PRACTICE_TABLE_NAME = "practice";
    private static final String PRACTICE_PRACTICE_ID = "practiceId";
    private static final String PRACTICE_QUESTION = "question";
    //Database table question
    private static final String LESSON_TABLE_NAME = "lesson";
    private static final String LESSON_LESSON_ID = "lessonId";
    private static final String LESSON_LESSON = "lesson;";

    //Create Table Statement - Profile
    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE " + PROFILE_TABLE_NAME + "(" +
            PRACTICE_PRACTICE_ID + " INTEGER PRIMARY KEY," + PRACTICE_QUESTION + " TEXT" + ");";

    //Create Table Statement - Lesson
    private static final String CREATE_TABLE_LESSON = "CREATE TABLE " + LESSON_TABLE_NAME + "(" +
            LESSON_LESSON_ID + " INTEGER PRIMARY KEY," + LESSON_LESSON + " BLOB" + ")";

    //Create Table Statement - Practice
    private static final String CREATE_TABLE_PRACTICE = "CREATE TABLE " + PRACTICE_TABLE_NAME + "(" +
            PRACTICE_PRACTICE_ID + " INTEGER PRIMARY KEY," + PRACTICE_QUESTION + " BLOB" + ")";

    //Constructor
    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the tables
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_LESSON);
        db.execSQL(CREATE_TABLE_PRACTICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table if exist
        db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LESSON_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PRACTICE_TABLE_NAME);
        //Create the tables
        onCreate(db);

    }
}
