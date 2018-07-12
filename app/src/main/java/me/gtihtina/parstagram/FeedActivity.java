package me.gtihtina.parstagram;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import me.gtihtina.parstagram.model.Post;

public class FeedActivity extends AppCompatActivity {

    private ParseApp parseapp;
    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    // Specify which class to query
    ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        //find the recyclerview
        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
        //init the arraylist(data source)
        posts = new ArrayList<>();
        //construct the adapter
        postAdapter = new PostAdapter(posts);
        //setup the recyclerview
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        //set the adapter
        rvPosts.setAdapter(postAdapter);
        populateFeed();

    }

    private void populateFeed() {


        // Specify the object id
        query.getInBackground("aFuEsvjoHt", new GetCallback<Post>() {
            @Override
            public void done(Post item, com.parse.ParseException e) {
                if (e == null) {
                    // Access data using the `get` methods for the object
                    String String = item.getDescription();
                    String objectId = item.getObjectId();
                    ParseUser username = item.getUser();
                    Date createdAt = item.getCreatedAt();
                    ParseFile image = item.getImage();
                    // Do whatever you want with the data...

                } else {
                    Log.d("FeedActivity", "Failed to get items from parse");
                }
            }

        });

    }



}
