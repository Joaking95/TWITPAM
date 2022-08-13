package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    //pass in the context, and list of tweets

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }


    //for each row, inflate the layout

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.item_tweets,parent,false);
        return new ViewHolder(view);
    }

    //Bind values in the position element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data position
        Tweet tweet = tweets.get(position);
        // bind the tweet with the view holder
        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    //define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{
         ImageView profileIma;
         TextView tvBody;
         TextView tvNom;
         TextView tvName;
         TextView tvSymbol;
         TextView tvClock;
         LinearLayout container;
         ImageView media_url;
         ImageView share;
         ImageView favorite;
         ImageView repeat;
         ImageView chat;
         TextView repeatUP;
         TextView favoriteUp;
         TextView chatUp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileIma = itemView.findViewById(R.id.profileIma);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvNom = itemView.findViewById(R.id.tvNom);
            tvSymbol = itemView.findViewById(R.id.tvAkoS);
            tvClock = itemView.findViewById(R.id.tvClock);
            media_url = itemView.findViewById(R.id.media_url);
            chatUp = itemView.findViewById(R.id.tvCountCh);
            repeatUP = itemView.findViewById(R.id.tvCountRep);
            favoriteUp = itemView.findViewById(R.id.tvCountFav);
            tvName = itemView.findViewById(R.id.tvName);
            container = itemView.findViewById(R.id.Container);
            share = itemView.findViewById(R.id.ic_share);
            repeat = itemView.findViewById(R.id.ic_repeat);
            chat =itemView.findViewById(R.id.ic_chat);
            favorite = itemView.findViewById(R.id.ic_favorite);

        }

        public void bind(Tweet tweet) {
            String PostImage = tweet.media_url;
            tvNom.setText(tweet.user.screenName);
            tvName.setText(tweet.user.name);
            repeatUP.setText(String.valueOf( tweet.retweet_count));
            chatUp.setText(String.valueOf( tweet.reply_count));
            favoriteUp.setText(String.valueOf( tweet.favorite_count));
            tvClock.setText(tweet.getFormattedTimestamp());
            tvBody.setText(tweet.body);
            media_url.setVisibility(View.GONE);

            Glide.with(context).load(tweet.user.imageUrl)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(180))).into(profileIma);
            try {
                List<String> ms = tweet.medias;
                if (!ms.isEmpty()) {
                    List<String> m = Arrays.asList(ms.get(0).split(" - "));
                    if (m.get(1).equals("photo")) {
                        media_url.setVisibility(View.VISIBLE);
                        Glide.with(context).load(m.get(0)).transform(new FitCenter(), new RoundedCorners(12))
                                .override(Target.SIZE_ORIGINAL).into(media_url);
                    }
                }
            } catch (Exception e) {}
            repeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "retweet",Toast.LENGTH_SHORT).show();
                    repeatUP.setText(String.valueOf(tweet.retweet_count +1) );

                }
            });
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkRippleAnimation(view);
                }


                public void checkRippleAnimation(View view){
                    Intent intent = new Intent(context, TweetdetailActivity.class);
                    intent.putExtra("tweet", Parcels.wrap(tweet));
                    context.startActivity(intent);
                }
            });
        }
    }
}
