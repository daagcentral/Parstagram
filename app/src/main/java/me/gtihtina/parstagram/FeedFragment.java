package me.gtihtina.parstagram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;

import java.util.ArrayList;
import java.util.List;

import me.gtihtina.parstagram.model.Post;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    //private ParseApp parseapp;
    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    SwipeRefreshLayout swipeContainer;

    // Specify which class to query
    //ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feed, null );


        //find the recyclerview
        rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);
        //init the arraylist(data source)
        posts = new ArrayList<>();
        //construct the adapter
        postAdapter = new PostAdapter(posts);
        //setup the recyclerview
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        //set the adapter
        rvPosts.setAdapter(postAdapter);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimeRefresh(0);
            }

        });


        loadTopPosts();

        // Inflate the layout for this fragment
        return view;
    }

    private void fetchTimeRefresh(int page) {

        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser().newestFirst();

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, com.parse.ParseException e) {
                if (e == null) {

                    posts.clear();
                    posts.addAll(objects);
                    //notify the adapter that we've added changed the dataset
                    postAdapter.notifyDataSetChanged();

                    swipeContainer.setRefreshing(false);
                } else {
                    Log.d("FeedFragment", "Fetch timeline error: " + e.toString());
                    e.printStackTrace();
                }

            }

        });


    }

    private void loadTopPosts() {

        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser().newestFirst();

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, com.parse.ParseException e) {
                if (e == null) {
                    posts.addAll(objects);
                    postAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }

            }

        });


    }

}
