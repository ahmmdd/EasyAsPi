/*
*   Name: MainActivity.java
*   Description: Main Activity Class
*   Last Modified: 2017, March 24
*   Last Modified By: Taera Kwon
 */

package team2.com.easyaspi;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import team2.com.easyaspi.databasePackage.ChapterBean;
import team2.com.easyaspi.databasePackage.TopicBean;
import team2.com.easyaspi.lessonsPackage.LessonsFragment;
import team2.com.easyaspi.lessonsPackage.TopicsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentInteractionListener,
        LessonsFragment.OnListFragmentInteractionListener, TopicsFragment.OnListFragmentInteractionListener {
    // Private Variable
    // Set String
    private String sPreviousTitle = "";
    private String sTitle = "";

    /*
    *   ON CREATE METHOD
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // Get the value from the intent
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fragment
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = MainFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // Define and set NavigationView (Left navigation panel)
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        // Counter to count fragments
        int fragCount = getSupportFragmentManager().getBackStackEntryCount();

        if (fragCount > 0){
            if (fragCount == 1){
                sPreviousTitle = "Easy as PI";
            }
            // Overriding
            super.onBackPressed();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            getSupportActionBar().setTitle(sPreviousTitle);
        }
        else {
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
    }

    /*
    * Displays Menu Item When Hamburger Button is Clicked
     */
    // Handle navigation view item clicks here.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        changeView(item.getItemId());
        return true;
    }

    // CUSTOM METHOD TO CHANGE FRAGMENT
    public void changeView(int viewId){
        Fragment fragment = null;
        Class fragmentClass = null;
        sTitle = getString(R.string.app_name);
        sPreviousTitle = sTitle;
        // SWITCH statement for Nav
        switch (viewId) {
            case R.id.nav_backToMain:
                fragmentClass = MainFragment.class;
                sTitle = "Easy as PI";
                break;
            case R.id.nav_lesson:
                fragmentClass = LessonsFragment.class;
                sTitle = "Lessons";
                break;
            case R.id.nav_logout:
                // When user presses Logout button
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        // if yes
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        // If no
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
        // If fragmentClass not null
        if (fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // If fragment is not empty
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment)
                    .addToBackStack(null) // Puts fragment into stack so back button goes back to previous state
                    .commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        // Change the toolbar title
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(sTitle);
        }
    }

    // Overriding onFragmentInteraction from MainFragment
    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    // Listener for Lessons list fragment
    @Override
    public void onListFragmentInteraction(ChapterBean chapter) {
        try{
            // Grabs topics list
            List<TopicBean> allTopicsList = LessonsFragment.topicsList;
            // Creates new topicsList object that will store topic object from forloop at below
            // after comparing integer value of chapter
            List<TopicBean> topicsList = new ArrayList<>();
            for (int counter = 0; counter < allTopicsList.size(); counter++){
                // If chapter name equals
                if (allTopicsList.get(counter).getChapter() == chapter.getChapter()) {
                    topicsList.add(allTopicsList.get(counter)); // Add topic to topicsList
                }
            }
            if (topicsList != null) {
                // Pass listValue to TopicsFragment at its instance
                Fragment fragment = null;
                fragment = TopicsFragment.newInstance(topicsList);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment)
                        .addToBackStack(null) // Puts fragment into stack so back button goes back to previous state
                        .commit();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                sPreviousTitle = getSupportActionBar().getTitle().toString();
                sTitle = "Topics for Chapter # " + chapter.getChapter();
                // Change the toolbar title
                if (getSupportActionBar() != null){
                    getSupportActionBar().setTitle(sTitle);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Listener for Topics list fragment
    @Override
    public void onListFragmentInteraction(TopicBean item) {
    }
}
