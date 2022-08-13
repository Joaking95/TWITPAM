package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bumptech.glide.load.model.ByteArrayLoader;
import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "UserId"))
public class Tweet {


    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public  String createdAt;

    @ColumnInfo
    public  String media_url;

    @ColumnInfo
    public int reply_count;

    @ColumnInfo
    public int retweet_count;

    @ColumnInfo
    public int favorite_count;
    public long UserId;
    
    @Ignore
    public User user;


    @TypeConverters(Convert.class)
    @ColumnInfo
    public List<String> medias = new ArrayList<>();

    public Tweet() {

    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user;
        tweet.UserId = user.id;
        tweet.id = jsonObject.getLong("id");

        try {
            //tweet.reply_count = jsonObject.getInt("reply_count");
            tweet.favorite_count = jsonObject.getInt("favorite_count");
            tweet.retweet_count = jsonObject.getInt("retweet_count");
            JSONArray Action_Tweet = jsonObject.getJSONObject("user").getJSONArray("entities");
            for (int i=0; i<Action_Tweet.length(); i++){
                tweet.reply_count= Action_Tweet.getJSONObject(i).getInt("reply_count");
                tweet.retweet_count = Action_Tweet.getJSONObject(i).getInt("retweet_count");
                tweet.favorite_count = Action_Tweet.getJSONObject(i).getInt("favorite_count");
            }
        } catch (Exception e){}


        // Takes media for this tweet
        try {
            JSONArray entities_media = jsonObject.getJSONObject("extended_entities").getJSONArray("media");
            for (int i = 0; i < entities_media.length(); i++) {
                String m = "";
                m += entities_media.getJSONObject(i).getString("media_url_https");
                m += " - ";
                m += entities_media.getJSONObject(0).getString("type");
                tweet.medias.add(m);
            }

        } catch (Exception e) {e.printStackTrace(); }

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }

        return tweets;
    }



    public String getFormattedTimestamp(){
        return TimeFormatter.getTimeDifference(createdAt);
    }

    public String getTimestamp(){
        return TimeFormatter.getTimeStamp(createdAt);
    }
}
