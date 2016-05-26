package com.codelabs.androidnavigationdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codelabs.androidnavigationdrawer.fragment.CreateFragment;
import com.codelabs.androidnavigationdrawer.fragment.DrawerItemCustomAdapter;
import com.codelabs.androidnavigationdrawer.fragment.HelpFragment;
import com.codelabs.androidnavigationdrawer.fragment.ReadFragment;

public class MainActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    //used to store app title
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for proper titles
        mTitle = mDrawerTitle = getTitle();

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_item_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // list the drawer items
        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_copy, "Create");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_refresh, "Read");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_action_share, "Help");

        // Pass the folderData to out ListView adapter
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        // Set the adapter for the list view
        mDrawerList.setAdapter(adapter);
        //set the item click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // for app icon control for nav drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            // called when a drawer has settled in a completly open state
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };

        //set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // enable ActionBar app icon to behave as action to toggle new drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null){
            //on first time display view for first nav item
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //The action bar home/up action should open or close the drawer
        // ActionBarDrawerToggle will take care of this
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // to change up caret
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occured
        mDrawerToggle.syncState();
    }

    // navigation drawer click listener
    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        // update the main content by replacing fragment

        Fragment fragment = null;

        switch (position){
            case 0 :
                fragment = new CreateFragment();
                break;
            case 1 :
                fragment = new ReadFragment();
                break;
            case 2 :
                fragment = new HelpFragment();
                break;
            default:
                break;
        }

        if (fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frmae, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.e("Main Activity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
       getActionBar().setTitle(mTitle);
    }
}