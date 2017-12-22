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

public class Update_Marks extends AppCompatActivity {

String rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__marks);
        Bundle extras = getIntent().getExtras();
        rollno=extras.getString("rollno");

    }
    public void up_submit(View view)
    {
        //EditText rollnofield = (EditText) findViewById(R.id.rollno);
        //String rollno = rollnofield.getText().toString();
        EditText ct_marks_field = (EditText) findViewById(R.id.ct_marks);
        String ct_marks=(ct_marks_field.getText().toString());
        EditText se_marks_field = (EditText) findViewById(R.id.se_marks);
        String se_marks=se_marks_field.getText().toString();
        EditText ia_marks_field = (EditText) findViewById(R.id.ia_marks);
        String ia_marks=(ia_marks_field.getText().toString());
        EditText see_marks_field= (EditText) findViewById(R.id.see_marks);
        String see_marks=(see_marks_field.getText().toString());


        Response.Listener<String> responseonmarks=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonResponse=new JSONObject(response);
                    boolean successonandroid=jsonResponse.getBoolean("keyfromphpsuccess");
                    if(successonandroid)
                    {
                        Toast.makeText(Update_Marks.this,"Marks Entered Successfully",Toast.LENGTH_LONG).show();


                        finish();
                    }
                    else
                    {
                        Toast.makeText(Update_Marks.this,"failed to register please try agian",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Update_Marks.this,"error in json exception",Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        };
        marks_request  marksupdate = new marks_request(rollno,ct_marks, se_marks, ia_marks,see_marks, responseonmarks);
        RequestQueue queue = Volley.newRequestQueue(Update_Marks.this);
        queue.add(marksupdate);





















        /*if(ct_marks!=0)
        {
            Toast.makeText(this, "Update in CT", Toast.LENGTH_SHORT).show();
        }
        else if(se_marks!=0)
        {
            Toast.makeText(this, "Update in SE", Toast.LENGTH_SHORT).show();
        }
        else if(ia_marks!=0)
        {
            Toast.makeText(this, "Update in IA", Toast.LENGTH_SHORT).show();
        }
        else if(see_marks!=0)
        {
            Toast.makeText(this, "Update in SEE", Toast.LENGTH_SHORT).show();
        }*/
    }
}
