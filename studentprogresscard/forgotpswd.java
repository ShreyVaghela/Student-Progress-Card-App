package com.example.android.studentprogresscard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class forgotpswd extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private String subject="Password";
    private String message="Your Password is password123.";

    //Send button
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpswd);

        //Initializing the views
        editTextEmail = (EditText) findViewById(R.id.email);

        buttonSend = (Button) findViewById(R.id.forgot_button);

        //Adding click listener
        buttonSend.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        String email = editTextEmail.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
    public void recoverpswd(View view)
    {
        Toast.makeText(this, "Sending email", Toast.LENGTH_SHORT).show();
    }
}

