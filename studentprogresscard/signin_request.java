package com.example.android.studentprogresscard;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shrey on 28/03/2017.
 */
public class signin_request extends StringRequest {
    //static String url = "http://justmakeitfast.esy.es/justmakeit/register.php";
    static String url = "https://15bit064.000webhostapp.com/shrey/login.php";

    private Map<String, String> params;

    public signin_request(String email,String pswd, Response.Listener<String> listener) {
        super(Request.Method.POST,url, listener, null);
        params = new HashMap<>();


        params.put("keyonandroidemail",email);
        params.put("keyonandroidpassword", pswd);
        //params.put("keyonandroidrollno",rollno);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
