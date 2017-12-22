package com.example.android.studentprogresscard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ViewMarks extends AppCompatActivity {

    TextView cetext,setext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_marks);
    }
    public void cebutton(View view)
    {
        Intent g = getIntent();
        String classtest = g.getStringExtra("classtest");
        String sessional= g.getStringExtra("sessional");
        String innovative= g.getStringExtra("innovative");
        String message=cemsg(classtest,sessional,innovative);
        cedisplay(message);
    }
    public void sebutton(View view)
    {
        Intent g = getIntent();
        String semend = g.getStringExtra("semend");
        String msg=semsg(semend);
        sedisplay(msg);
    }
    public void progress(View view)
    {
        /*Intent g = getIntent();
        String classtest = g.getStringExtra("classtest");
        String sessional= g.getStringExtra("sessional");
        String innovative= g.getStringExtra("innovative");
        String semend = g.getStringExtra("semend");*/
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());
        graph.addSeries(series);
    }
    private DataPoint[] getDataPoint(){

        Intent g = getIntent();
        String classtest = g.getStringExtra("classtest");
        String sessional= g.getStringExtra("sessional");
        String innovative= g.getStringExtra("innovative");
        String semend = g.getStringExtra("semend");
        int total=Integer.parseInt(classtest)+Integer.parseInt(sessional)+Integer.parseInt(innovative);
        DataPoint[] dp=new DataPoint[]{
                new DataPoint(0, (Integer.parseInt(classtest)*100)/30),
                new DataPoint(2, (Integer.parseInt(sessional)*100)/40),
                new DataPoint(4, (Integer.parseInt(innovative)*100)/30),
                new DataPoint(6, total),
                new DataPoint(8, Integer.parseInt(semend))
        };
        return (dp);
    }

    private String cemsg(String classtest,String sessional,String innovative)
    {
        int total=Integer.parseInt(classtest)+Integer.parseInt(sessional)+Integer.parseInt(innovative);
        String msg="Classtest(0):"+classtest;
        msg+="\n\nSessioanl(2):"+sessional;
        msg+="\n\nInnovative(4):"+innovative;
        msg+="\n\nTotal(6):"+total;
        return msg;
    }
    private String semsg(String semend)
    {
        String msg="Marks(8):";
        msg+=semend;
        return msg;
    }
    private void cedisplay(String msg)
    {
        cetext = (TextView) findViewById(R.id.ce);

        cetext.setText(msg);
    }

    private void sedisplay(String msg)
    {
        setext = (TextView) findViewById(R.id.se);

        setext.setText(msg);
    }
}
