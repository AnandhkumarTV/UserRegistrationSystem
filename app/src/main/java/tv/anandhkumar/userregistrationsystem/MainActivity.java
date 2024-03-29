package tv.anandhkumar.userregistrationsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button login,signup;
    EditText username,password;

    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();



        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        Toast.makeText(this, "You has already logged in..!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,SignupActivity.class);
//                startActivity(i);
//            }
//        });
    }


    public void onRegister(View view){

        final String myEmail = username.getText().toString();
        final String myPassword = password.getText().toString();

        if (myEmail.isEmpty() && myPassword.isEmpty()){
            Toast.makeText(this,"Please enter username and password",Toast.LENGTH_SHORT).show();
        }
        else if (myEmail.isEmpty()){
            Toast.makeText(this,"Please enter username",Toast.LENGTH_SHORT).show();
        }
        else if (myPassword.isEmpty()){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(myEmail, myPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("TAG", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Successfully Registered"+user, Toast.LENGTH_SHORT).show();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

    public void onLogin(View view) {

        final String myEmail = username.getText().toString();
        final String myPassword = password.getText().toString();

        if (myEmail.isEmpty() && myPassword.isEmpty()){
            Toast.makeText(this,"Please enter username and password",Toast.LENGTH_SHORT).show();
        }
        else if (myEmail.isEmpty()){
            Toast.makeText(this,"Please enter username",Toast.LENGTH_SHORT).show();
        }
        else if (myPassword.isEmpty()){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(myEmail, myPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Logged as: " + user, Toast.LENGTH_SHORT).show();
                                //updateUI(user);
                                Intent in = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(in);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }
}
