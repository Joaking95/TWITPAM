package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class TimelineActivity extends AppCompatActivity {
    public static final String TAG = "TimelineActivity";
    TwitterClient client;
    RecyclerView rvTweets;
    List<Tweet> tweets;
    TweetsAdapter adapter;
    SwipeRefreshLayout swiperefresh;
    EndlessRecyclerViewScrollListener scrollListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client = TwitterApp.getRestClient(this );
        swiperefresh =findViewById(R.id.SwipeContainer);
        swiperefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                Log.i("TAG", "FETCH NEW DATA");
                populateHomeTimeline();
            }
        });
        //find the recyclerview
        rvTweets = findViewById(R.id.rvTweets);
        //Init the list of tweets and adapter
        tweets = new ArrayList<>();
        adapter = new TweetsAdapter(this, tweets);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //recyclerview setup: layout manager in the adapter
        rvTweets.setLayoutManager(layoutManager);
        rvTweets.setAdapter(adapter);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i("good", "Onloadmore"+page);
                loadMoreData();

            }
        };

        // Adds the scroll listener to RecyclerView
        rvTweets.addOnScrollListener(scrollListener);
        populateHomeTimeline();
    }

    private void loadMoreData() {

        // Send an API request to retrieve appropriate paginated data
         client.getNextPageOfTweets(new JsonHttpResponseHandler() {
             @Override
             public void onSuccess(int statusCode, Headers headers, JSON json) {
                 Log.i("more", "More_Data" +json.toString());

                 //  --> Deserialize and construct new model objects from the API response
                 JSONArray jsonArray = json.jsonArray;

                 try {
                     List<Tweet>  tweets = Tweet.fromJsonArray(jsonArray);
                     //  --> Append the new data objects to the existing set of items inside the array of items
                     //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()
                     adapter.addAll(tweets);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }


             }

             @Override
             public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                 Log.e("more", "Less_Data",throwable);
             }
         },tweets.get(tweets.size()-1).id);

    }

    private void populateHomeTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.i(TAG, "SUCCES" + json.toString());
                JSONArray jsonArray = json.jsonArray;
                try {
                    adapter.clear();
                    adapter.addAll(Tweet.fromJsonArray(jsonArray));
                    swiperefresh.setRefreshing(false);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.i(TAG, "FAIL" +response,throwable);
            }
        });
    }
}