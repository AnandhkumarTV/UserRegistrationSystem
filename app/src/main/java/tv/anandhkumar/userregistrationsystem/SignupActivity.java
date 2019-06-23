package tv.anandhkumar.userregistrationsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    EditText mUsername,mEmail,mPhoneno;
    Button signup;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference userRef = rootRef.child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsername = findViewById(R.id.username);
        mEmail = findViewById(R.id.email);
        mPhoneno = findViewById(R.id.phoneno);
        signup = findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String myUsername = mUsername.getText().toString().trim();
                final String myEmail = mEmail.getText().toString().trim();
                final String myPhoneno = mPhoneno.getText().toString().trim();

                final HashMap<String, String> userMap = new HashMap<String, String>();
                userMap.put("Username",myUsername);
                userMap.put("Email", myEmail);
                userMap.put("Phone No", myPhoneno);


                if (myUsername.isEmpty() && myPhoneno.isEmpty() && myEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }

                else if (myUsername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the Username",Toast.LENGTH_SHORT).show();
                }

                else if (myEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the Email",Toast.LENGTH_SHORT).show();
                }

                else if (myPhoneno.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the Phoneno",Toast.LENGTH_SHORT).show();
                }

                else {

                    userRef.push().setValue(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(SignupActivity.this, RetrivalActivity.class);
                            startActivity(in);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}
