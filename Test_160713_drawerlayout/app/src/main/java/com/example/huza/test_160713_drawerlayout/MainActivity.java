package com.example.huza.test_160713_drawerlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    class customAdapter extends BaseAdapter {

        String item[] = {"apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "strawberry"};
        String price[] = {"300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "300", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "apple", "melon", "strawberry"};

        @Override
        public int getCount() {
            return item.length;
        }

        @Override
        public Object getItem(int position) {
            return item[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CustomListView view = new CustomListView(getApplicationContext());
            view.setName(item[position]);
            view.setPrice(price[position]);


            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customAdapter ca = new customAdapter();

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(ca);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
