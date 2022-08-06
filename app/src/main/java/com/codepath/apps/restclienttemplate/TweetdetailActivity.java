package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetdetailActivity extends AppCompatActivity {

    TextView tvCorps;
    TextView tvName;
    TextView tvScreenname;
    ImageView ProfilImageD;
    TextView tvSymbol;
    TextView tvClockD;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweetdetail);
        tvCorps = findViewById(R.id.tvCorpsD);
        tvScreenname =findViewById(R.id.tvScreen_NameD);
        tvName = findViewById(R.id.tvNameD);
        ProfilImageD = findViewById(R.id.profileImaD);
        tvSymbol = findViewById(R.id.tvAkoS);
        tvClockD = findViewById(R.id.tvClockD);
        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvCorps.setText(tweet.body);
        tvScreenname.setText(tweet.user.screenName);
        tvName.setText(tweet.user.name );
        tvClockD.setText(tweet.getTimestamp());
        Glide.with(this).load(tweet.user.imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(180))).into(ProfilImageD);


    }
}
