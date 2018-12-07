package com.example.admin.humnum;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    //
    MaterialEditText passwordEditText;
    MaterialEditText phoneEditText;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        passwordEditText = (MaterialEditText) findViewById(R.id.password_edit_text);
        phoneEditText = (MaterialEditText) findViewById(R.id.phone_edit_text);

        signInButton = (Button) findViewById(R.id.sign_in_button_sign_in_layout);

        //Init Database FireBase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userTable = database.getReference("User");

        //SIG IN BUTTON ACTION LISTENER
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override

        //
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(SignIn.this);
                dialog.setMessage("WAIT ... ");
                dialog.show();

                userTable.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(phoneEditText.getText().toString()).exists())
                        {

                            //RETRIEVE USER INFORMATION FROM DATABASE - FIREBASE BY CREATING CLASS User
                            dialog.dismiss();
                            User user = dataSnapshot.child(phoneEditText.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(passwordEditText.getText().toString()))
                            {
                                Toast.makeText(SignIn.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(SignIn.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            dialog.dismiss();
                            Toast.makeText(SignIn.this, "User does not exists", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
