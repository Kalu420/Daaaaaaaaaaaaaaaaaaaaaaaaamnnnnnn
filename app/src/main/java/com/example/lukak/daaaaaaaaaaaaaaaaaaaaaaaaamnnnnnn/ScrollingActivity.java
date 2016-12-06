package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScrollingActivity extends Activity {
    MyDBHalper statHelp;
    ProgressBar p1,p2,p3;
    TextView t1,t2,t3;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        DateFormat df = new SimpleDateFormat("dd MM yyyy");
        final String date = df.format(Calendar.getInstance().getTime());
        statHelp=new MyDBHalper(this,null,null,1);
        Intent intent =getIntent();
        id=intent.getIntExtra("id",id);
        String [][]p=statHelp.statistika(id,date,0);
        int rez[]=new int[2];
        if(p.length!=0)
       rez=progVal(p);
        String [][]q=statHelp.statistika(id,date,1);
        int rez1[]=new int[2];
        if(q.length!=0)
                rez1=progVal(q);
        String [][]r=statHelp.statistika(id,date,2);
        int rez2[]=new int[2];
        if(r.length!=0)
                rez2=progVal(r);
       Toast.makeText(ScrollingActivity.this,Integer.toString(rez2[1])+" "+Integer.toString(rez1[1])+" "+Integer.toString(rez[1]),Toast.LENGTH_SHORT).show();
        p1= (ProgressBar) findViewById(R.id.progressBar);
        p2= (ProgressBar) findViewById(R.id.progressBar1);
        p3= (ProgressBar) findViewById(R.id.progressBar2);

        t1= (TextView) findViewById(R.id.textView);
        t2= (TextView) findViewById(R.id.textView1);
        t3= (TextView) findViewById(R.id.textView2);
        float xD=0;
        if(rez[1]!=0)
        xD=100*rez[0]/rez[1];
        t1.setText(Integer.toString(rez[0])+" minuta od "+Integer.toString(rez[1])+"\n"+xD+"%");
        float xD1=0;
        if(rez1[1]!=0)
            
        xD1=100*rez1[0]/rez1[1];
        t2.setText(Integer.toString(rez1[0])+" minuta od "+Integer.toString(rez1[1])+"\n"+xD1+"%");
        float xD2=0;
        if(rez2[1]!=0)
        xD2=100*rez2[0]/rez2[1];
        t3.setText(Integer.toString(rez2[0])+" minuta od "+Integer.toString(rez2[1])+"\n"+xD2+"%");
        if(rez[1]!=0)
        p1.setProgress(rez[0]*100/rez[1]);p1.setMax(100);
        if(rez1[1]!=0)
        p2.setProgress(rez1[0]*100/rez1[1]);p2.setMax(100);
        if(rez2[1]!=0)
        p3.setProgress(rez2[0]*100/rez2[1]);p3.setMax(100);
    }


    public int [] progVal(String [] [] str) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String test = sdf.format(c.getTime());
        int prog=0,max=0;
        int cur=toMins(test);
        if(str!=null)
       for(int j=0;j<str.length;j++)
       for(int i=0;i<14;i+=2) {
         //  if(str[i]!=null&&str[i+1]!=null)
            if (cur > toMins(str[j][i]) && cur < toMins(str [j][i+1])) {
                prog = cur - toMins(str[j][i]);
                max=toMins(str[j][i+1])-toMins(str[j][i]);
                int [] re= new int[]{prog,max};
                return re;
            }
        }
       int [] re= new int[]{prog,max};
        return re;
    }

    public int toMins (String str){
        int p=0;
        String [] parts=new String[]{};
        if(str!=null)
        if(!str.isEmpty())
        {
        parts=str.split(":");
        String x;
        parts[0]=parts[0].substring(0,2);
        if(parts[0].startsWith("0"))
           parts[0]=parts[0].substring(1);
        parts[1]=parts[1].substring(0,2);
        if(parts[1].startsWith("0"))
            parts[1]=parts[1].substring(1);
        p=60*Integer.parseInt(parts[0])+Integer.parseInt(parts[1]);
        }
        return p;
    }

}
