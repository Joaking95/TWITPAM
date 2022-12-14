package com.codepath.apps.restclienttemplate.models;

import androidx.room.Embedded;

import java.util.ArrayList;
import java.util.List;

public class TweetWithUser {

    // @Embedded notation flattens the properties of the User object into the object, preserving encapsulation.
    @Embedded
    User user;

    // Prefix is needed to resolve ambiguity between fields: user.id and tweet.id, user.createdAt and tweet.createdAt
    @Embedded(prefix = "tweet_")
    Tweet tweet;

    public static List<Tweet> getTweetlist(List<TweetWithUser> tweetWithUser) {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < tweetWithUser.size(); i++) {
            Tweet tweet = tweetWithUser.get(i).tweet;
            tweet.user = tweetWithUser.get(i).user;
            tweets.add(tweet);
        }
        return tweets;

    }
}
