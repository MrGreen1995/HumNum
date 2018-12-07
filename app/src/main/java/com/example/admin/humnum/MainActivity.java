package com.example.admin.humnum;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    //DECLARE WIDGETS
    Button signUpButton;
    Button signInButton;

    TextView sloganTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INITIALIZE WIFGRTS
        signUpButton = (Button) findViewById(R.id.sig_up_button);
        signInButton = (Button) findViewById(R.id.sig_in_button);

        sloganTextView = (TextView) findViewById(R.id.slogan_text_view);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/LEMONJELLY.TTF");
        sloganTextView.setTypeface(face);


        //SIGN UP BUTTON ACTION
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUp);
            }
        });


        //SIGN IN BUTTON ACTION
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(signIn);
            }
        });

    }
}
