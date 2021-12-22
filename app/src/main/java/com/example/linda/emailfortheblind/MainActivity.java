package com.example.linda.emailfortheblind;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import android.support.multidex.MultiDexApplication;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class app{}

public class MainActivity extends AppCompatActivity {
    SignInButton signinbutton;
    GoogleSignInClient googleClient;
    TextView Title, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Title = findViewById(R.id.title);
        Title.setText("Welcome To Email for the Blind");
        login = findViewById(R.id.login);
        login.setText("Kindly Login");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleClient = GoogleSignIn.getClient(this, gso);
        signinbutton = findViewById(R.id.sign_in_button);
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 101){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try{
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(this, "Email"+account.getEmail(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "name"+account.getDisplayName(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "userId"+account.getId(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }catch (ApiException e){
//            log.w("Google SignIn Error", "SignInResult: failed code =" + e.getStatusCode());
            Toast.makeText(MainActivity.this,   "Failed"+ e.getStatusCode(), Toast.LENGTH_LONG).show();
        }
    }
}
