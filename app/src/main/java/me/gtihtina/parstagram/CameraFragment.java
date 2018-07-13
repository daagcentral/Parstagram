package me.gtihtina.parstagram;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;

import me.gtihtina.parstagram.model.Post;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

    interface Callback {
        void onPostCreated();
    }

    private Callback callback;

    private EditText descriptionInput;
    private Button picbtn;
    private ImageView postPic;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Callback) {
            callback = (Callback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        callback = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera, null );
        picbtn = view.findViewById(R.id.postbtn);
        descriptionInput = view.findViewById(R.id.etDescription);
        postPic = view.findViewById(R.id.ivPost2);

        picbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // onLaunchCamera();
                Bitmap bitmap = ((BitmapDrawable) postPic.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);


                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();
                final ParseFile parsefile = new ParseFile(byteArrayOutputStream.toByteArray());

                parsefile.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        createPost(description, parsefile, user);

                    }
                });
            }
        });
        return view;
    }

    private void createPost(String description, ParseFile imageFile, ParseUser user) {
        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);
        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("HomeActivity", "Create item_post success!");
                    callback.onPostCreated();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }




}

