package com.example.android.studentprogresscard;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.provider.BaseColumns;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class signin extends Activity {

    int flag=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }
    public void signIn(final View view)
    {
        String rollno;
        EditText emailField=(EditText) findViewById(R.id.email);
        String email=emailField.getText().toString();
        int len=email.length();
        if(email.substring(0,2).equals("15") && email.substring(len-15,len).equals("@nirmauni.ac.in"))
        {
            flag=1;

        }
        else if((!(email.substring(0,2).equals("15"))) && email.substring(len-15,len).equals("@nirmauni.ac.in"))
        {
            flag=0;
        }
        else
        {
            flag=-1;
        }
        EditText pswdField=(EditText) findViewById(R.id.password);
        String pswd=pswdField.getText().toString();

        if(flag==1)
        {
            rollno=email.substring(0,8);
            Response.Listener<String> responseonsignin=new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try
                    {
                        JSONObject jsonResponse=new JSONObject(response);
                        boolean successonandroid=jsonResponse.getBoolean("keyfromphpsuccess");
                        //String markscalsstest=jsonResponse.getString("keyfromphpclass");
                        String classtest=jsonResponse.getString("keyfromphpclasstest");
                        String sessional=jsonResponse.getString("keyfromphpsessional");
                        String innovative=jsonResponse.getString("keyfromphpinnovative");
                        String semend=jsonResponse.getString("keyfromphpsemend");
                        if(successonandroid)
                        {
                            Toast.makeText(signin.this,"Sign-In Successfull...",Toast.LENGTH_LONG).show();
                            if(flag==1) {
                                if (view.getId() == R.id.button) {
                                    Intent i = new Intent(signin.this, ViewMarks.class);
                                    //i.putExtra("rollno",rollno);
                                    i.putExtra("classtest", classtest);
                                    i.putExtra("sessional", sessional);
                                    i.putExtra("innovative", innovative);
                                    i.putExtra("semend", semend);
                                    startActivity(i);
                                }
                            }

                        }
                        else
                        {
                            Toast.makeText(signin.this,"failed to sign in please try agian",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        //Toast.makeText(signin.this,"error in json exception",Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }
                }
            };
            signin_request  signinRequest = new signin_request(email,pswd,responseonsignin);
            RequestQueue queue = Volley.newRequestQueue(signin.this);
            queue.add(signinRequest);

            faculty_page_request  facultypageRequest = new faculty_page_request(rollno,responseonsignin);
            RequestQueue q = Volley.newRequestQueue(signin.this);
            q.add(facultypageRequest);
        }
        else if(flag==0) {
            Response.Listener<String> responseonsignin = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean successonandroid = jsonResponse.getBoolean("keyfromphpsuccess");

                        if (successonandroid) {
                            Toast.makeText(signin.this, "Sign-In Successfull...", Toast.LENGTH_LONG).show();

                            if (flag == 0) {
                                if (view.getId() == R.id.button) {
                                    Intent i = new Intent(signin.this, faculty_page.class);
                                    startActivity(i);
                                }
                            }

                        } else {
                            Toast.makeText(signin.this, "failed to sign in please try agian", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        //Toast.makeText(signin.this, "error in json exception", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }
                }
            };
            signin_request  signinRequest = new signin_request(email,pswd,responseonsignin);
            RequestQueue queue = Volley.newRequestQueue(signin.this);
            queue.add(signinRequest);
        }

        /*if(flag==1)
        {
            signin_request  signinRequest = new signin_request(email,pswd,responseonsignin);
            RequestQueue queue = Volley.newRequestQueue(signin.this);
            queue.add(signinRequest);

            faculty_page_request  facultypageRequest = new faculty_page_request(rollno,responseonsignin);
            RequestQueue q = Volley.newRequestQueue(signin.this);
            q.add(facultypageRequest);
        }
        else if(flag==0)
        {
            signin_request  signinRequest = new signin_request(email,pswd,responseonsignin);
            RequestQueue queue = Volley.newRequestQueue(signin.this);
            queue.add(signinRequest);

        }*/


    }






// Insert the new row, returning the primary key value of the new row

    private String printmessage(String email,String pswd,int flag)
    {
        String message="\nEmail:"+email;
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
    private void display(String message)
    {
        TextView TextView = (TextView) findViewById(R.id.text_view);
        TextView.setText(message);
    }
    public void forgotpassword(View view)
    {
        if(view.getId()==R.id.forgot_pswd)
        {
            Intent i=new Intent(this,forgotpswd.class);
            startActivity(i);
        }
    }
}



