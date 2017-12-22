package com.example.android.studentprogresscard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void signUp(View view)
    {
        if(view.getId()==R.id.signup) {
            Intent i = new Intent(this, signup.class);
            startActivity(i);
        }

    }
    public void signIn(View view)
    {
        if(view.getId()==R.id.signin) {
            Intent i = new Intent(this, signin.class);
            startActivity(i);

        }
    }
}
