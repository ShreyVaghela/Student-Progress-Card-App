package com.example.android.studentprogresscard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class faculty_page extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_page);
    }
    public void newentry(View view)
    {    EditText rollnofield = (EditText) findViewById(R.id.rollno);
        String rollno = rollnofield.getText().toString();

        if(view.getId()==R.id.new_entry)
        {
            Intent i = new Intent(this, Update_Marks.class);
            i.putExtra("rollno",rollno);
            startActivity(i);
        }
    }
    public void updatemarks(View view)
    { EditText rollnofield = (EditText) findViewById(R.id.rollno);
        String rollno = rollnofield.getText().toString();

        if(view.getId()==R.id.update_marks)
        {
            Intent i = new Intent(this, Update_Marks.class);
            i.putExtra("rollno",rollno);
            startActivity(i);
        }
    }
    public void viewmarks(final View view)
    {
        EditText rollnofield = (EditText) findViewById(R.id.rollno);
        final String rollno = rollnofield.getText().toString();

        Response.Listener<String> responseonview=new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonResponse=new JSONObject(response);
                    boolean successonandroid=jsonResponse.getBoolean("keyfromphpsuccess");
                    String classtest=jsonResponse.getString("keyfromphpclasstest");
                    String sessional=jsonResponse.getString("keyfromphpsessional");
                    String innovative=jsonResponse.getString("keyfromphpinnovative");
                    String semend=jsonResponse.getString("keyfromphpsemend");
                    if(successonandroid)
                    {
                        //Toast.makeText(faculty_page.this,"View Successfull...",Toast.LENGTH_LONG).show();
                        if(view.getId()==R.id.view_marks)
                        {
                            Intent i = new Intent(faculty_page.this, ViewMarks.class);
                            //i.putExtra("rollno",rollno);
                            i.putExtra("classtest",classtest);
                            i.putExtra("sessional",sessional);
                            i.putExtra("innovative",innovative);
                            i.putExtra("semend",semend);
                            startActivity(i);
                        }

                    }
                    else
                    {
                        Toast.makeText(faculty_page.this,"failed to sign in please try agian",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(faculty_page.this,"error in json exception",Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        };
        faculty_page_request  facultypageRequest = new faculty_page_request(rollno,responseonview);
        RequestQueue queue = Volley.newRequestQueue(faculty_page.this);
        queue.add(facultypageRequest);
    }
}
