package com.codepath.apps.restclienttemplate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.TweetDao;
import com.codepath.apps.restclienttemplate.models.TweetWithUser;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class TimelineActivity extends AppCompatActivity {
    public static final String TAG = "TimelineActivity";
    private final int REQUEST_CODE = 20;
    TwitterClient client;
    RecyclerView rvTweets;
    List<Tweet> tweets;
    TweetsAdapter adapter;
    SwipeRefreshLayout swiperefresh;
    EndlessRecyclerViewScrollListener scrollListener;
    TweetDao tweetDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client = TwitterApp.getRestClient(this );
        tweetDao = ((TwitterApp) getApplicationContext()).getMyDatabase().tweetDao();
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //find the recyclerview
        rvTweets = findViewById(R.id.rvTweets);
        //Init the list of tweets and adapter
        tweets = new ArrayList<>();
        adapter = new TweetsAdapter(this, tweets);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //recyclerview setup: layout bvmanager in the adapter
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

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "SHOW DATABASE");
                List<TweetWithUser> tweetWithUser =  tweetDao.recentItems();
                List<Tweet> tweetsFromDB = TweetWithUser.getTweetlist(tweetWithUser);
                adapter.clear();
                adapter.addAll(tweetsFromDB);
            }
        });
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

    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.compose){
            Intent i = new Intent(this,ComposeActivity.class);
            startActivityForResult(i,REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode== REQUEST_CODE && resultCode == REQUEST_CODE){
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            tweets.add(0, tweet);
            adapter.notifyItemInserted(0);
            rvTweets.smoothScrollToPosition(0);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void populateHomeTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.i(TAG, "SUCCES" + json.toString());
                JSONArray jsonArray = json.jsonArray;
                try {

                    List<Tweet> tweetsFromNet = Tweet.fromJsonArray(jsonArray);
                    adapter.clear();
                    adapter.addAll(tweetsFromNet);
                    swiperefresh.setRefreshing(false);
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "SAVING DATABASE");
                            List<User> usersFromNet =User.fromJsonTweetArray(tweetsFromNet);

                            tweetDao.insertModel(usersFromNet.toArray(new User[0]));
                            tweetDao.insertModel(tweetsFromNet.toArray(new Tweet[0]));

                        }
                    });

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