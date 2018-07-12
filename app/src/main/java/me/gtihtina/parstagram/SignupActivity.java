package me.gtihtina.parstagram;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

public class SignupActivity extends AppCompatActivity {

    private EditText etMobile;
    private EditText etName;
    private EditText etUsername;
    private EditText etPassword1;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etMobile = findViewById(R.id.etMobile);
        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword1 = findViewById(R.id.etPassword1);
        btnSignup = findViewById(R.id.signupbtnn);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                    // Set core properties
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword1.getText().toString());
                user.setEmail(etMobile.getText().toString());
                    // Set custom properties
                //user.put("phone", "650-253-0000");
                    // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Log.d("SignupActivity", "Sign up successful.");
                            final Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Log.d("SignupActivity", "Login failed");
                            e.printStackTrace();
                        }
                    }


                });
            }
        });



    }
}
