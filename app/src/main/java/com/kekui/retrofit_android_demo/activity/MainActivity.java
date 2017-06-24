package com.kekui.retrofit_android_demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kekui.retrofit_android_demo.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.kekui.retrofit_android_demo.adapter.CommitsAdapter;
import com.kekui.retrofit_android_demo.model.ItemResponse;
import com.kekui.retrofit_android_demo.model.Item_;

import com.kekui.retrofit_android_demo.rest.CommitApiService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    private final static String REPO_PATH = "repo:square/retrofit merge:false sort:author-date-desc";
    private final static String HEADER_AUTH = "application/vnd.github.cloak-preview";

    //use http://www.jsonschema2pojo.org/ to gernerate Java Objects from JSON

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectAndGetApiData();
    }

    // This method create an instance of Retrofit
    // set the base url
    public void connectAndGetApiData(){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        CommitApiService commitApiService = retrofit.create(CommitApiService.class);
        

        Call<ItemResponse> call = commitApiService.getReposCommits(HEADER_AUTH,REPO_PATH);

        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                List<Item_> commits = response.body().getItems();

                recyclerView.setAdapter(new CommitsAdapter(commits, R.layout.list_item_commit, getApplicationContext()));
                Log.d(TAG, "Number of commits received: " + commits.size());

            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
