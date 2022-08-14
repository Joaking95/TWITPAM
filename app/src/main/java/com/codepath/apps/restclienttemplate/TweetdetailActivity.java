package com.codepath.apps.restclienttemplate;


import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
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
    ImageView profileImaR;
    ImageView media_urlR;
    ImageView ic_chatR;
    ImageView ic_favoriteR;
    ImageView ic_repeatR;
    ImageView ic_shareR;
    EditText footer;
    TextView tvCountRepR;
    TextView tvCountFavR;
    TextView tvCountChR;
    TextView tvNameR;
    TextView tvSymbolR;
    TextView tvClockR;
    TextView tvCorpsR;
    TextView tvNomR;
    TextView tvSymbol;
    TextView tvClockD;


    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweetdetail);

        tvNameR = findViewById(R.id.tvNameR);
        tvNomR = findViewById(R.id.tvNomR);
        profileImaR = findViewById(R.id.profileImaR);
        ic_chatR = findViewById(R.id.ic_chatR);
        ic_favoriteR = findViewById(R.id.ic_favoriteR);
        ic_repeatR = findViewById(R.id.ic_repeatR);
        ic_shareR = findViewById(R.id.ic_shareR);
        footer = findViewById(R.id.replyEt);
        tvCountRepR = findViewById(R.id.tvCountRepR);
        tvCountFavR = findViewById(R.id.tvCountFavR);
        tvCountChR = findViewById(R.id.tvCountChR);
        tvSymbolR = findViewById(R.id.tvAkoSR);
        tvClockR = findViewById(R.id.tvClockR);
        tvCorpsR = findViewById(R.id.tvBodyR);



        tvName = findViewById(R.id.tvNameD);
        ProfilImageD = findViewById(R.id.profileImaD);
        tvSymbol = findViewById(R.id.tvAkoS);
        tvClockD = findViewById(R.id.tvClockD);
        tvCorps = findViewById(R.id.tvCorpsD);
        tvScreenname =findViewById(R.id.tvScreen_NameD);


        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvCorps.setText(tweet.body);
        //tvCorps.setText(tweet.body);
        tvScreenname.setText(tweet.user.screenName);
        tvName.setText(tweet.user.name );
        tvClockD.setText(tweet.getTimestamp());
        Glide.with(this).load(tweet.user.imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(180))).into(ProfilImageD);

        tvCountRepR.setText(String.valueOf(tweet.retweet_count));
        tvCountFavR.setText(String.valueOf(tweet.favorite_count));
        tvCountChR.setText(String.valueOf(tweet.reply_count));
    }
}
