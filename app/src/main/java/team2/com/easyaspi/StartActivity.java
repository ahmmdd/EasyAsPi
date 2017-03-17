/*
*   Name: StartActivity.java
*   Description: Start Activity Class that displays when App is launched
*   Last Modified: 2017, March 04
*   Last Modified By: Taera Kwon
 */


package team2.com.easyaspi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import team2.com.easyaspi.databasePackage.XmlParser;


public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        try {
            InputStream iStream = getBaseContext().getAssets().open("grade1_lessons.xml");
            XmlParser xmlParser = new XmlParser();
            HashMap<String, List> parsed = xmlParser.GradeParser(iStream);
            xmlParser.GradeParser(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    // When Back Button is pressed
    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit the application?")
                .setMessage("Are you sure you want to close the application?")
                // if yes
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        moveTaskToBack(true); // Completely closes application
                    }
                })
                // If no
                .setNegativeButton("No", null)
                .show();
    }

    // When Login Button  is pressed
    public void onLoginPressed(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
