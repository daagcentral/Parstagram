package me.gtihtina.parstagram.model;

import android.text.format.DateUtils;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@ParseClassName("Post")

public class Post extends ParseObject {

    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_USER = "user";
    private static final String KEY_OBJECTID = "objectId";


    public String getDescription(){

        return getString(KEY_DESCRIPTION);

    }

    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);

    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);

    }

    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);

    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);

    }
    public void setUser(ParseUser user){
        put(KEY_USER, user);

    }



    public String getRelativeTimeAgo() {

        return DateUtils.getRelativeTimeSpanString(getCreatedAt().getTime(),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
    }


    @Override
    public String getObjectId() {
        return super.getObjectId();
    }

    public static class Query extends ParseQuery<Post>{

        public Query() {
            super(Post.class);
        }

        public Query newestFirst(){
            orderByDescending("createdAt");
            return this;
        }

        public Query getTop(){
            setLimit(20);
            return this;
        }
        public Query withUser(){
            include("user");
            return this;
        }
    }


}
