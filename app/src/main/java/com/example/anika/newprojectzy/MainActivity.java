package com.example.anika.newprojectzy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anika.newprojectzy.addAdvertising.AddAdvertisingActivity;
import com.example.anika.newprojectzy.advertising.AdvertisingListActivity;
import com.example.anika.newprojectzy.advokat.AdvokatActivity;
import com.example.anika.newprojectzy.drawer.DataModel;
import com.example.anika.newprojectzy.drawer.DrawerItemCustomAdapter;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    LinearLayout relAdvokat;
    private RelativeLayout relMain, relSelect, relObyavlenia, relProizvodstvo, relAdvertising, relResume, relVacansy;
    private RelativeLayout lin;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_NAME = "name";
    private static final String APP_PREFERENCES_CODE = "code";
    private static final String APP_PREFERENCES_ENG = "eng";
    private SharedPreferences mSettings;
    ImageView logoToolBar;
    TextView titleToolBar;
    String engName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        lin = (RelativeLayout) findViewById(R.id.linDrawer) ;
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        engName = mSettings.getString(APP_PREFERENCES_ENG,"");
        if(engName.isEmpty()) engName = "minsk";
        setupToolbar();

        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.ic_account_balance_black_24dp, "Connect");
        drawerItem[1] = new DataModel(R.drawable.ic_account_balance_black_24dp, "Fixtures");
        drawerItem[2] = new DataModel(R.drawable.ic_account_balance_black_24dp, "Table");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        titleToolBar = (TextView) findViewById(R.id.title_toolbar1);
        logoToolBar = (ImageView) findViewById(R.id.logoToolBar);
        setupDrawerToggle();
        setUpMenu();


        Fragment fragment = new  MainFragment(this, engName);
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.relMain:
                setUpFragmentMain();
                setCickMenu(view);
                break;
            case R.id.relSelect:
                setCickMenu(view);
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
                break;
            case R.id.linAdvokat:
                setCickMenu(view);
                startActivity(new Intent(MainActivity.this, AdvokatActivity.class));
                break;
            case R.id.relAddAdvertising:
                setCickMenu(view);
                startActivity(new Intent(MainActivity.this, AddAdvertisingActivity.class));
                break;
            case R.id.relObyavlenia:
                setCickMenu(view);
                Intent intent = new Intent(MainActivity.this, AdvertisingListActivity.class);
                intent.putExtra("id","1");
                intent.putExtra("pid","1");
                startActivity(intent);
                break;
            case R.id.relResume:
                setCickMenu(view);
                Intent intent2 = new Intent(MainActivity.this, AdvertisingListActivity.class);
                intent2.putExtra("id","2");
                startActivity(intent2);
                break;
            case R.id.relVacansy:
                setCickMenu(view);
                Intent intent3 = new Intent(MainActivity.this, AdvertisingListActivity.class);
                intent3.putExtra("id","3");
                startActivity(intent3);
                break;
        }


    }



    private void setCickMenu(View view){
        view.setBackgroundColor(Color.parseColor("#AAAAAA"));
    }

    private void setUpFragmentMain(){
        Fragment fragment = new  MainFragment(this, engName);
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        try {

            Fragment fragment = null;


            switch (position) {
                case 0:
                    fragment = new MainFragment(this, engName);
                    break;
                case 1:
                    //fragment = new MainFragment();
                    break;
                case 2:
                    //fragment = new MainFragment();
                    break;

                default:
                    break;
            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                setTitle(mNavigationDrawerItemTitles[position]);
                mDrawerLayout.closeDrawer(lin);

            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setUpMenu(){
        relAdvokat = (LinearLayout) findViewById(R.id.linAdvokat);
        relMain = (RelativeLayout) findViewById(R.id.relMain);
        relSelect = (RelativeLayout) findViewById(R.id.relSelect);
        relObyavlenia = (RelativeLayout) findViewById(R.id.relObyavlenia);
        relProizvodstvo = (RelativeLayout) findViewById(R.id.relProizvodstvo);
        relAdvertising = (RelativeLayout) findViewById(R.id.relAddAdvertising);
        relResume = (RelativeLayout) findViewById(R.id.relResume);
        relVacansy = (RelativeLayout) findViewById(R.id.relVacansy);


        relAdvokat.setOnClickListener(this);
        relMain.setOnClickListener(this);
        relSelect.setOnClickListener(this);
        relObyavlenia.setOnClickListener(this);
        relProizvodstvo.setOnClickListener(this);
        relAdvertising.setOnClickListener(this);
        relResume.setOnClickListener(this);
        relVacansy.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        setUpMenu();
        //API.getWeather(this,engName, titleToolBar);

    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    public void setWeather(String data, String img) {
        titleToolBar.setText(data);
        try {
            Picasso.with(MainActivity.this)
                    .load(img)
                    .into(logoToolBar);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}