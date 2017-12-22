package com.example.android.studentprogresscard;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shrey on 18/04/2017.
 */

public class marks_request extends StringRequest{

    static String url = "https://15bit064.000webhostapp.com/shrey/mark.php";

    private Map<String, String> params;

    public marks_request(String rollno, String classtest,String sessional,String innovaive, String semend, Response.Listener<String> listener) {
        super(Request.Method.POST,url, listener, null);
        params = new HashMap<>();

        params.put("rollno", rollno);
        params.put("classtest", classtest);
        params.put("sessional", sessional);
        params.put("innovative", innovaive);
        params.put("semend",semend);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

