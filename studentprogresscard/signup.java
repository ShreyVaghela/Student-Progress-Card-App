package com.example.android.studentprogresscard;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.android.studentprogresscard.R;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.id.message;

public class signup extends Activity {

    int flag=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUp(final View view) {

        EditText emailField = (EditText) findViewById(R.id.email);
        String email = emailField.getText().toString();
        EditText semfield = (EditText) findViewById(R.id.sem);
        String sem = semfield.getText().toString();
        int len = email.length();
        int s=Integer.parseInt(sem);
        if (email.substring(0,2).equals("15") && email.substring(len-15, len).equals("@nirmauni.ac.in") && s>0 && s<9) {
            flag = 1;

        } else if ((!(email.substring(0, 2).equals("15"))) && email.substring(len - 15, len).equals("@nirmauni.ac.in")) {
            flag = 0;
        } else {
            flag = -1;
        }


        EditText pswdField = (EditText) findViewById(R.id.password);
        String pswd = pswdField.getText().toString();
        EditText namefield = (EditText) findViewById(R.id.name);
        String name = namefield.getText().toString();
        EditText rollnofield = (EditText) findViewById(R.id.rollno);
        String rollno = rollnofield.getText().toString();

        EditText batchfield = (EditText) findViewById(R.id.batch);
        String batch = batchfield.getText().toString();
        String message = printmessage(name, rollno, sem, batch, email, pswd, flag);
        if(flag==-1)
        {
            Toast.makeText(signup.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
        }
        else {

            Response.Listener<String> responseonsignup = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean successonandroid = jsonResponse.getBoolean("keyfromphpsuccess");
                        if (successonandroid) {

                            if (flag == 1) {
                                Toast.makeText(signup.this, "Sign Up success full...", Toast.LENGTH_LONG).show();
                                if (view.getId() == R.id.signup) {
                                    Intent i = new Intent(signup.this, signin.class);
                                    startActivity(i);
                                }
                            } else if (flag == 0) {
                                Toast.makeText(signup.this, "Sign Up success full...", Toast.LENGTH_LONG).show();
                                if (view.getId() == R.id.signup) {
                                    Intent i = new Intent(signup.this, signin.class);
                                    startActivity(i);
                                }
                            }

                            finish();
                        } else {
                            Toast.makeText(signup.this, "failed to register please try agian", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(signup.this, "error in json exception", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }
                }
            };
            signup_request signupRequest = new signup_request(name, rollno, sem, email, pswd, responseonsignup);
            RequestQueue queue = Volley.newRequestQueue(signup.this);
            queue.add(signupRequest);


        }





















      /*  if(flag==1)
        {
            if(view.getId()==R.id.signup) {
                Intent i = new Intent(this, marks.class);
                startActivity(i);
            }
        }
        else if(flag==0)
        {
            if(view.getId()==R.id.signup) {
                Intent i = new Intent(this, faculty_page.class);
                startActivity(i);
            }
        }*/

    }
    private String printmessage(String name,String rollno,String sem,String batch,String email,String pswd,int flag)
    {
        String message="\nName:"+name;
        message+="\nRoll No:"+rollno;
        message+="\nSemester:"+sem;
        message+="\nBatch:"+batch;
        message+="\nEmail:"+email;
        message+="\nPassword:"+pswd;
        if(flag==1)
        {
            message+="\nStudent";
        }
        else if(flag==0)
        {
            message+="\nFaculty";
        }
        else
        {
            message+="\nInvalid email";
        }
        return message;
    }

    private void display(String message) {
        TextView TextView=(TextView) findViewById(R.id.text_view);
        TextView.setText(message);
    }
}
