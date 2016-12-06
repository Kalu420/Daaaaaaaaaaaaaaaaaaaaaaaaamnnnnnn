package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Konfiguracija extends AppCompatActivity {
    final public static int SEND_SMS = 101;
    TextView dateText;
    String valueBr="1111",value,poruka="",minfix,hfix,On="On",Off="Off",test[],test1,DATE1=null,ToastString="",cntr="",cntr1="";
    Cursor res;
    ToggleButton togSMS,togBlue,toglitmin;
    EditText brum,brojText;
    StringBuffer buf=new StringBuffer();
    Button Ok,Cancel,confirm;
    MyDBHalper helpConf;
    Dialog dialog;
    Toast tag;
    Boolean []bool=new Boolean[2];
    Boolean []bool1=new Boolean[2];
    TimePicker timePicker;
    int id,brr=0;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfiguracija);
        Intent intent=getIntent();
        helpConf=new MyDBHalper(this,null,null,1);
        id=intent.getIntExtra("id",id);
        valueBr=intent.getStringExtra("valbr");
        dateText= (TextView) findViewById(R.id.timeDateText);
        brojText= (EditText) findViewById(R.id.brojText);
        confirm= (Button) findViewById(R.id.confirmButton);
        togBlue= (ToggleButton) findViewById(R.id.toggleBlue);
        togSMS= (ToggleButton) findViewById(R.id.toggleSMS);
        toglitmin= (ToggleButton) findViewById(R.id.toggle_litri_min);
        if(wtf(helpConf.helpC(id))[0].contains("On"))
            togSMS.setChecked(true);
        if(wtf(helpConf.helpC(id))[1].contains("On"))
            togBlue.setChecked(true);
        if(helpConf.bazalitmin(id).contains("lit"))
            toglitmin.setChecked(true);
        brum= (EditText) findViewById(R.id.brUmnozakaText);
        brum.setText(wtf(helpConf.helpC(id))[2]);
        test=new String[3];
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dialog = new Dialog(Konfiguracija.this);
                dialog.setContentView(R.layout.date_time_dialog);
                dialog.setTitle("Configure Time and Date");
                Window window=dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                timePicker=(TimePicker)dialog.findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);
                datePicker=(DatePicker)dialog.findViewById(R.id.datePicker);
                dialog.show();
                Ok= (Button)dialog.findViewById(R.id.dateTimeOk);
                Cancel= (Button)dialog.findViewById(R.id.dateTimeCancel);
                Ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int   day  = datePicker.getDayOfMonth();
                        int   month= datePicker.getMonth();
                        int   year = datePicker.getYear();
                        int min=timePicker.getCurrentMinute();
                        if(min<10)
                        minfix="0"+min;
                        else minfix=Integer.toString(min);
                        int hour=timePicker.getCurrentHour();
                        if(hour<10)
                        hfix="0"+hour;
                        else hfix=Integer.toString(hour);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                        String formatedDate = sdf.format(new Date(year, month, day));
                        dateText.setText(hfix+":"+minfix+" "+formatedDate.toString());
                        DATE1="D,V "+hfix+":"+minfix+" "+"D "+formatedDate.toString()+";";
                       // dateText.setText(Integer.toString(min));
                        dialog.cancel();
                    }
                });
                Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

            }
        });




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHalper hh= new MyDBHalper(getApplicationContext(),null,null,1);
                if(togSMS.isChecked()&&wtf(hh.helpC(id))[0].contains("Off")){
                    hh.getWritableDatabase().execSQL("UPDATE konfiguracija SET SMS= " +" ' "+On+" ' " + " WHERE _id= "+" ' "+ id +" ' ");
                    poruka="S,0;";
                    checkAndroidVersion();
                    //Toast.makeText(getApplicationContext(),"SMS izvjestaj ukljucen",Toast.LENGTH_SHORT).show();
                    ToastString+="SMS izvjestaj ukljucen\n";
                }
                if((!togSMS.isChecked()&&wtf(hh.helpC(id))[0].contains("On"))) {
                    hh.getWritableDatabase().execSQL("UPDATE konfiguracija SET SMS= " + " ' "+Off+" ' " + " WHERE _id= " + " ' " + id + " ' ");
                    poruka="S,1;";
                    checkAndroidVersion();
                   // Toast.makeText(getApplicationContext(),"SMS izvjestaj iskljucen",Toast.LENGTH_SHORT).show();
                    ToastString+="SMS izvjestaj iskljucen\n";
                }
                if(togBlue.isChecked()&&wtf(hh.helpC(id))[1].contains("Off")){
                    hh.getWritableDatabase().execSQL("UPDATE konfiguracija SET Blue= " +" ' "+On+" ' " + " WHERE _id= "+" ' "+ id +" ' ");
                    poruka="B,0;";
                    checkAndroidVersion();
                   // Toast.makeText(getApplicationContext(),"Slanje na Blue Leaf ukljuceno",Toast.LENGTH_SHORT).show();
                    ToastString+="Slanje na Blue Leaf ukljuceno\n";
                }

                if((!togBlue.isChecked())&&wtf(hh.helpC(id))[1].contains("On")){
                    hh.getWritableDatabase().execSQL("UPDATE konfiguracija SET Blue= " +" ' "+Off+" ' " + " WHERE _id= "+" ' "+ id +" ' ");
                    poruka="B,1;";
                    checkAndroidVersion();
                   // Toast.makeText(getApplicationContext(),"Slanje na Blue Leaf iskljuceno",Toast.LENGTH_SHORT).show();
                    ToastString+="Slanje na Blue Leaf iskljuceno\n";
                }
                int x=Integer.parseInt(brum.getText().toString().trim());
                int x1=Integer.parseInt(wtf(hh.helpC(id))[2].trim());
                if(x!=x1) {
                    poruka ="V,"+x+";";
                    checkAndroidVersion();
                   hh.getWritableDatabase().execSQL("UPDATE konfiguracija SET BRUM= " + " ' " + brum.getText().toString() + " ' " + " WHERE _id= " + " ' " + id + " ' ");
                  //  Toast.makeText(getApplicationContext(),"Koeficijent vodomjera promijenjen",Toast.LENGTH_SHORT).show();
                    ToastString+="Koeficijent vodomjera promijenjen\n";
                }

                if(toglitmin.isChecked()&&hh.bazalitmin(id).contains("min")){
                    poruka="T,1;";
                    checkAndroidVersion();
                    hh.getWritableDatabase().execSQL("UPDATE kaluTable SET toggle= " + " ' " +"lit"+ " ' " + " WHERE _id= " + " ' " + id + " ' ");
                  //  Toast.makeText(getApplicationContext(),"Nacin rada podesen na minute",Toast.LENGTH_SHORT).show();
                    ToastString+="Nacin rada podesen na minute\n";

                }
                if(!toglitmin.isChecked()&&hh.bazalitmin(id).contains("lit")){
                    poruka="T,0;";
                    checkAndroidVersion();
                    hh.getWritableDatabase().execSQL("UPDATE kaluTable SET toggle= " + " ' " +"min"+ " ' " + " WHERE _id= " + " ' " + id + " ' ");
                    //Toast.makeText(getApplicationContext(),"Nacin rada podesen na minute",Toast.LENGTH_SHORT).show();
                    ToastString+="Nacin rada podesen na minute\n";

                }
                if(DATE1!=null){
                    poruka=DATE1;
                    checkAndroidVersion();
                   // Toast.makeText(getApplicationContext(),"Datum podesen",Toast.LENGTH_SHORT).show();
                    ToastString+="Datum podesen\n";
                }

                if(brojText.getText().toString().matches("[+]3826[0-9]{7,11}")) {
                    cntr=cntr1;
                    cntr1 ="P,"+brojText.getText().toString();
                    if(!cntr1.equals(cntr)){
                    poruka=cntr1+";";
                    checkAndroidVersion();
                   // Toast.makeText(getApplicationContext(),"Broj za SMS izvjestaj podesen",Toast.LENGTH_SHORT).show();
                    ToastString+="Broj za SMS izvjestaj podesen\n";
                    }

                }
                if(!ToastString.isEmpty()){

                    tag = Toast.makeText(getApplicationContext(),ToastString,Toast.LENGTH_SHORT);

                    tag.show();

                    new CountDownTimer(5000, 1000)
                    {

                        public void onTick(long millisUntilFinished) {tag.show();}
                        public void onFinish() {tag.show();}

                    }.start();
                    ToastString="";
                }
                   // for (int i=0; i < 2; i++)
                //Toast.makeText(getApplicationContext(),ToastString,Toast.LENGTH_LONG).show();
                //ToastString="";
            }
        });



    }


    public void checkAndroidVersion(){
        if (Build.VERSION.SDK_INT >= 13) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(Konfiguracija.this, Manifest.permission.SEND_SMS);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Konfiguracija.this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS);
                return;
            }else{
                SmsManager sm = SmsManager.getDefault();
                sm.sendTextMessage(valueBr,null, poruka, null, null);
            }
        } else {
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(valueBr, null,poruka, null, null);
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(valueBr, null, poruka, null, null);
                } else {


                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public boolean checker(String s){
        if(s.equals("Off"))
            return false;
         return true;

    }
    public String  [] wtf(Cursor c){
        String [] p=new String[3];
        while(c.moveToNext()){
            p[0]=c.getString(1);
            p[1]=c.getString(2);
            p[2]=c.getString(3);

        }
        return p;

    }
}


