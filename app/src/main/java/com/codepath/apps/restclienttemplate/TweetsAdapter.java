package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;
import org.w3c.dom.Text;

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
         RelativeLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileIma = itemView.findViewById(R.id.profileIma);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvNom = itemView.findViewById(R.id.tvNom);
            tvName = itemView.findViewById(R.id.tvName);
            container = itemView.findViewById(R.id.Container);
        }

        public void bind(Tweet tweet) {
            tvNom.setText(tweet.user.screenName);
            tvName.setText(tweet.user.name);
            tvBody.setText(tweet.body);
            Glide.with(context).load(tweet.user.imageUrl)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(180))).into(profileIma);
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
