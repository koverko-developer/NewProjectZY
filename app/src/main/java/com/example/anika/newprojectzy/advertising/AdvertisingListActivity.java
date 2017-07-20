package com.example.anika.newprojectzy.advertising;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.anika.newprojectzy.AllInfoActivity;
import com.example.anika.newprojectzy.OnLoadMoreListener;
import com.example.anika.newprojectzy.R;
import com.example.anika.newprojectzy.retrofit.Advertising;
import com.example.anika.newprojectzy.retrofit.App;
import com.example.anika.newprojectzy.retrofit.Resume;
import com.example.anika.newprojectzy.retrofit.Vacansy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdvertisingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<AdvertisingObject> categoryList = new ArrayList<>();
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int ival = 1;
    private int loadLimit = 10;
    AdvertisingAdapter adapter;
    int count = 0;
    String pid = "";

    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_NAME = "name";
    private static final String APP_PREFERENCES_CODE = "code";
    private static final String APP_PREFERENCES_ENG = "eng";
    private static final String APP_PREFERENCES_C = "c";
    private SharedPreferences mSettings;
    String id,type;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String name = mSettings.getString(APP_PREFERENCES_NAME,"");
        if(name.isEmpty()) name = "Минск";

        getSupportActionBar().setTitle("Каталог Беларуси");


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
            switch (id){
                case "1":
                    toolbar.setSubtitle("Объявления");
                    pid = intent.getStringExtra("pid");
                    getDataAdvertising(categoryList.size(),Integer.parseInt(pid));
                    break;
                case "2":
                    toolbar.setSubtitle("Резюме");
                    getDataResume(categoryList.size());
                    break;
                case "3":
                    toolbar.setSubtitle("Вакансии");
                    getDataVacansy(categoryList.size());
                    break;
        }



//
//        AdvertisingObject advertisingObject1 = new AdvertisingObject();
//
//        advertisingObject1.setTitle(new ObservableField<String>("Title"));
//        advertisingObject1.setTown(new ObservableField<String>("Минск"));
//
//        categoryList.add(advertisingObject1);


        recyclerView = (RecyclerView) findViewById(R.id.rvListAdvertising);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);


        adapter = new AdvertisingAdapter(recyclerView,this,categoryList,this);
        recyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                String s = "";
                //Toast.makeText(AdvertisingListActivity.this, "END",Toast.LENGTH_SHORT).show();
                switch (id){
                    case "1":
                        pid = "1";
                        getDataAdvertising(count,1);
                        break;
                    case "2":
                        getDataResume(count);
                        break;
                    case "3":
                        getDataVacansy(count);
                        break;
                }

            }
        });

//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            int ydy = 0;
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int offset = dy - ydy;
//                ydy = dy;
//                boolean shouldRefresh = (llm.findFirstCompletelyVisibleItemPosition() == 0)
//                        && (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) && offset > 30;
//                if (shouldRefresh) {
//                    //swipeRefreshLayout.setRefreshing(true);
//                    //Refresh to load data here.
//                    return;
//                }
//                boolean shouldPullUpRefresh = llm.findLastCompletelyVisibleItemPosition() == llm.getChildCount() - 1
//                        && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING && offset < -30;
//                if (shouldPullUpRefresh) {
//                    //swipeRefreshLayout.setRefreshing(true);
//                    //refresh to load data here.
//                    return;
//                }
//                //.setRefreshing(false);
//            }
//        });

//        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//
//
//
//            }
//        });
        //getData(0,1);


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }

    public void startInfo(int position){
        Intent intent = new Intent(AdvertisingListActivity.this, AllInfoActivity.class);
        intent.putExtra("pid", pid);
        intent.putExtra("id", categoryList.get(position).getId().get());
        intent.putExtra("ids",String.valueOf(id));
        intent.putExtra("type","0");
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getDataAdvertising(int offset, int q){

        App.getApi().getAdvertising(offset,q).enqueue(new Callback<List<Advertising>>() {
            @Override
            public void onResponse(Call<List<Advertising>> call, retrofit2.Response<List<Advertising>> response) {

                List<Advertising> list = new ArrayList<Advertising>();
                list = response.body();
                String s = "";
                for(int i = 0; i< list.size(); i++){
                    AdvertisingObject object = new AdvertisingObject();
                    object.setTown(new ObservableField<String>(list.get(i).getTown()));
                    object.setTitle(new ObservableField<String>(list.get(i).getName()));
                    object.setImg(new ObservableField<String>(list.get(i).getImg()));
                    object.setId(new ObservableField<String>(list.get(i).getId()));
                    object.setDate(new ObservableField<String>(list.get(i).getDate()));
                    categoryList.add(object);
                    //ival++;

                }

                recyclerView.getAdapter().notifyDataSetChanged();
                adapter.setLoaded();

            }


            @Override
            public void onFailure(Call<List<Advertising>> call, Throwable t) {

            }
        });
        count+=5;
    }

    private void getDataResume(int offset){

        App.getApi().getResume(offset).enqueue(new Callback<List<Resume>>() {
            @Override
            public void onResponse(Call<List<Resume>> call, retrofit2.Response<List<Resume>> response) {

                List<Resume> list = new ArrayList<Resume>();
                list = response.body();
                String s = "";
                for(int i = 0; i< list.size(); i++){
                    AdvertisingObject object = new AdvertisingObject();
                    object.setTown(new ObservableField<String>(list.get(i).getTown()));
                    object.setTitle(new ObservableField<String>(list.get(i).getSpeciality()));
                    object.setImg(new ObservableField<String>(list.get(i).getImg()));
                    object.setId(new ObservableField<String>(list.get(i).getId()));
                    object.setDate(new ObservableField<String>(list.get(i).getDate()));
                    categoryList.add(object);
                    //ival++;

                }

                recyclerView.getAdapter().notifyDataSetChanged();
                adapter.setLoaded();

            }


            @Override
            public void onFailure(Call<List<Resume>> call, Throwable t) {

            }
        });
        count+=5;
    }

    private void getDataVacansy(int offset){

        App.getApi().getVacansy(offset).enqueue(new Callback<List<Vacansy>>() {
            @Override
            public void onResponse(Call<List<Vacansy>> call, retrofit2.Response<List<Vacansy>> response) {

                List<Vacansy> list = new ArrayList<Vacansy>();
                list = response.body();
                String s = "";
                for(int i = 0; i< list.size(); i++){
                    AdvertisingObject object = new AdvertisingObject();

                    object.setTown(new ObservableField<String>(list.get(i).getTown()));
                    object.setTitle(new ObservableField<String>(list.get(i).getName()));
                    object.setId(new ObservableField<String>(list.get(i).getId()));
                    object.setDate(new ObservableField<String>(list.get(i).getDate()));
                    categoryList.add(object);
                    //ival++;

                }

                recyclerView.getAdapter().notifyDataSetChanged();
                adapter.setLoaded();

            }


            @Override
            public void onFailure(Call<List<Vacansy>> call, Throwable t) {

            }
        });
        count+=5;
    }

}
