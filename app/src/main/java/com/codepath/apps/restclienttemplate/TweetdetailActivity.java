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

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetdetailActivity extends AppCompatActivity {

    TextView tvCorps;
    TextView tvName;
    TextView tvScreenname;
    ImageView ProfilImageD;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweetdetail);
        tvCorps = findViewById(R.id.tvCorpsD);
        tvScreenname =findViewById(R.id.tvScreen_NameD);
        tvName = findViewById(R.id.tvNameD);
        ProfilImageD = findViewById(R.id.profileImaD);
        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvCorps.setText(tweet.body);
        tvScreenname.setText(tweet.user.screenName);
        tvName.setText(tweet.user.name );


    }
}
