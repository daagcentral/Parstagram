package me.gtihtina.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.Parse.Configuration;
import com.parse.ParseObject;

import me.gtihtina.parstagram.model.Post;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        final Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("gtihtina")
                .clientKey("doodledash")
                .server("http://gtihtina-parstagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }


}
