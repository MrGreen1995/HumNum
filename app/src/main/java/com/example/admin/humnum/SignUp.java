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

public class SignUp extends AppCompatActivity {

    MaterialEditText passwordEditText;
    MaterialEditText phoneEditText;
    MaterialEditText nameEditText;
    Button signUnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        passwordEditText = (MaterialEditText) findViewById(R.id.password_edit_text_sign_up);
        phoneEditText = (MaterialEditText) findViewById(R.id.phone_edit_text_sign_up);
        nameEditText = (MaterialEditText) findViewById(R.id.name_edit_text_sign_up);
        signUnButton = (Button) findViewById(R.id.sign_up_button_sign_up_layout);


        //initialize database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userTable = database.getReference("User");



        signUnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("WAIT ... ");
                dialog.show();

                userTable.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if already exists
                        if(dataSnapshot.child(phoneEditText.getText().toString()).exists())
                        {
                            dialog.dismiss();
                            Toast.makeText(SignUp.this, "Number already exists", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            User user = new User(nameEditText.getText().toString(), passwordEditText.getText().toString());
                            userTable.child(phoneEditText.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "You are successfully signed!!!", Toast.LENGTH_SHORT).show();
                            finish();
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
