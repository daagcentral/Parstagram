package me.gtihtina.parstagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;

import java.util.List;

import me.gtihtina.parstagram.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> mPost;
    Context context;

    //pass in the posts array in the constructor
    public PostAdapter(List<Post> posts){

        mPost = posts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get tweet data according to position
        Post post = mPost.get(position);
        //populate the view according to the data
        holder.tvUsername.setText(post.getUser().getUsername());
        holder.tvDescription.setText(post.getDescription().toString());
        //holder.tvTime.setText(post.getRelativeTimeAgo(post.getTime()));
        try {
            Glide.with(context).load(post.getImage().getFile()).into(holder.ivPost);
        } catch (ParseException e) {
            // fuck you
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    //create viewHolder class

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivPost;
        public TextView tvUsername;
        public TextView tvDescription;


        public ViewHolder (View itemView){
            super(itemView);

            //preform the findViewById
            ivPost = (ImageView) itemView.findViewById(R.id.FrameLayout);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);



        }



    }

    public void clear() {
        mPost.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        mPost.addAll(list);
        notifyDataSetChanged();
    }

}
