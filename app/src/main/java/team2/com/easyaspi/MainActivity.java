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
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentInteractionListener, RecycledViewFragment.OnFragmentInteractionListener {
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
        int fragCount = getFragmentManager().getBackStackEntryCount();

        if (fragCount == 0){
            super.onBackPressed();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
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
        changeFragment(item.getItemId());
        return true;
    }

    // CUSTOM METHOD TO CHANGE FRAGMENT
    public void changeFragment(int viewId){
        Fragment fragment = null;
        Class fragmentClass = null;
        String title = getString(R.string.app_name);
        // SWITCH statement for Nav
        switch (viewId) {
            case R.id.nav_backToMain:
                fragmentClass = MainFragment.class;
                title = "Easy as PI";
                break;
            case R.id.nav_lesson:
                fragmentClass = RecycledViewFragment.class;
                title = "Lessons";
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
            getSupportActionBar().setTitle(title);
        }
    }

    // Overriding onFragmentInteraction from MainFragment
    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
