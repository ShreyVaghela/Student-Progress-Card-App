package com.example.android.studentprogresscard;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by d on 24-Mar-17.
 */

public class signup_request extends StringRequest {
    //static String url = "http://justmakeitfast.esy.es/justmakeit/register.php";
    static String url = "https://15bit064.000webhostapp.com/shrey/register.php";

    private Map<String, String> params;

    public signup_request(String fullname,String rollno,String sem, String email,String pswd, Response.Listener<String> listener) {
        super(Request.Method.POST,url, listener, null);
        params = new HashMap<>();


        params.put("keyonandroidfullname", fullname);
        params.put("keyonandroidrollno", rollno);
        params.put("keyonandroidsem", sem);
        params.put("keyonandroidemail",email);
        params.put("keyonandroidpassword", pswd);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
