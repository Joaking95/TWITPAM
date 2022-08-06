package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class User {
    public  String name;
    public String screenName;
    public String imageUrl;
    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.screenName = jsonObject.getString("screen_name");
        //user.Symbol = jsonObject.getJSONObject("symbols");
        user.imageUrl = jsonObject.getString("profile_image_url_https");
        return user;
    }
}
