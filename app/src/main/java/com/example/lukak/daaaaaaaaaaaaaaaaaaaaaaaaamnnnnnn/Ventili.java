package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ventili extends AppCompatActivity {
    String vent11="",vent23="",vent33="",vent12="",vent13="",vent21="",vent22="",vent31="",vent32="",value;
    String vent14="",vent15="",vent16="",vent24="",vent25="",vent26="",vent34="",vent35="",vent36="",valueBr,poruka="Test",isChecked;
    String vent17="",vent18="",vent19="",vent110="",vent27="",vent28="",vent29="",vent210="",vent37="",vent38="",vent39="",vent310="";
    String vent1on="",vent2on="",vent3on="",brV;
    String[] ventili= new String[]{"Ventil 1","Ventil 2","Ventil 3"},vent1=new String[]{"","","","","","","","","",""};
    String []vent2=new String[]{"","","","","","","","","",""},vent3=new String[]{"","","","","","","","","",""};
    ListView listaVentila;
    Button viewBtn,btn1,btn2;
    TextView edit1,edit2,edit3,edit4,edit5,edit6,edit7,edit8,edit9,edit10;
    MyDBHalper dbHalp;
    Window window;
    int brojac,id,lit_min;
    NewAdapter1 kalu;
    public ArrayList<String> data=new ArrayList<String>();
    final public static int SEND_SMS = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventili);
        ActionBar ab=getSupportActionBar();
        Intent intent = getIntent();
        value = intent.getStringExtra("key");
        valueBr=intent.getStringExtra("key1");
        id=intent.getIntExtra("id",id);
        ab.setSubtitle(value);
        dbHalp= new MyDBHalper (this,null,null,1);
        Cursor res=dbHalp.isTogChecked(id);
        while(res.moveToNext())
        isChecked=res.getString(0);
        if(isChecked.contains("lit"))
            lit_min=1;
        else lit_min=0;
        Toast.makeText(this,isChecked+lit_min,Toast.LENGTH_SHORT).show();
        data.add(ventili[0]);
        data.add(ventili[1]);
        data.add(ventili[2]);
        kalu= new NewAdapter1(this,R.layout.customventlist,data);
        listaVentila = (ListView) findViewById(R.id.listaVentila);
        listaVentila.setAdapter(kalu);
        viewBtn=(Button) findViewById(R.id.viewBtn);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brojac=0;
                Cursor res = dbHalp.getLastVent(id);
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    brojac++;
                    buffer.append("Ventil "+brojac+"\n");
                   if(!res.getString(3).isEmpty()) buffer.append("Paljenje: "+ res.getString(3)+"  ");
                    if(!res.getString(4).isEmpty()) buffer.append("Trajanje: "+ res.getString(4)+"\n");
                    if(!res.getString(5).isEmpty())  buffer.append("Paljenje: "+ res.getString(5)+"  ");
                    if(!res.getString(6).isEmpty())  buffer.append("Trajanje: "+ res.getString(6)+"\n");
                    if(!res.getString(7).isEmpty()) buffer.append("Paljenje: "+ res.getString(7)+"  ");
                    if(!res.getString(8).isEmpty()) buffer.append("Trajanje: "+ res.getString(8)+"\n");
                    if(!res.getString(10).isEmpty()) buffer.append("Paljenje: "+ res.getString(10)+"  ");
                    if(!res.getString(11).isEmpty()) buffer.append("Trajanje: "+ res.getString(11)+"\n");
                    if(!res.getString(12).isEmpty()) buffer.append("Paljenje: "+ res.getString(12)+"  ");
                    if(!res.getString(13).isEmpty()) buffer.append("Trajanje: "+ res.getString(13));
                    buffer.append("\n\n");

                }
                showMessage("Zadnja Konfiguracija",buffer.toString());
            }
        });

        listaVentila.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long l) {
                DateFormat df = new SimpleDateFormat("dd MM yyyy");
                final String date = df.format(Calendar.getInstance().getTime());
                final Dialog dialog= new Dialog(Ventili.this);
                window=dialog.getWindow();
                window.requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
                edit1=  (EditText) dialog.findViewById(R.id.editT1);edit1.setOnFocusChangeListener(focusChange);
                edit2=  (EditText) dialog.findViewById(R.id.editT2);edit2.setOnFocusChangeListener(focusChange);
                edit3=  (EditText) dialog.findViewById(R.id.editT3);edit3.setOnFocusChangeListener(focusChange);
                edit4=  (EditText) dialog.findViewById(R.id.editT4);edit4.setOnFocusChangeListener(focusChange);
                edit5=  (EditText) dialog.findViewById(R.id.editT5);edit5.setOnFocusChangeListener(focusChange);
                edit6=  (EditText) dialog.findViewById(R.id.editT6);edit6.setOnFocusChangeListener(focusChange);
                edit7=  (EditText) dialog.findViewById(R.id.editT7);edit7.setOnFocusChangeListener(focusChange);
                edit8=  (EditText) dialog.findViewById(R.id.editT8);edit8.setOnFocusChangeListener(focusChange);
                edit9=  (EditText) dialog.findViewById(R.id.editT9);edit9.setOnFocusChangeListener(focusChange);
                edit10=  (EditText) dialog.findViewById(R.id.editT10);edit10.setOnFocusChangeListener(focusChange);
                btn1= (Button) dialog.findViewById(R.id.btn1);
                btn2= (Button) dialog.findViewById(R.id.btn2);




                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(position==0  ) {
                            vent11= edit1.getText().toString();vent12=edit2.getText().toString();vent13=edit3.getText().toString();
                            vent14= edit4.getText().toString();vent15=edit5.getText().toString();vent16=edit6.getText().toString();
                            vent17= edit7.getText().toString();vent18=edit8.getText().toString();vent19=edit9.getText().toString();vent110=edit10.getText().toString();
                            vent1=new String[]{vent11,vent12,vent13,vent14,vent15,vent16,vent17,vent18,vent19,vent110};
                            fixStr();
                            int brint=brVent(vent1);
                            poruka=" ";
                            poruka=Integer.toString(position+1)+","+brint+";";
                            poruka+=format(lit_min,vent1);
                            vent1[1]=jesus(vent1[1]);vent1[3]=jesus(vent1[3]);vent1[5]=jesus(vent1[5]);vent1[7]=jesus(vent1[7]);vent1[9]=jesus(vent1[9]);

                            if(poruka.matches("[1-3],[1-5];([T] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]) [DK] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]);){1,5}")&&lit_min==0) {
                                checkAndroidVersion();
                                dialog.cancel();
                                dbHalp.addVentH(Integer.toString(position),value,vent1[0],vent1[1],vent1[2],vent1[3],vent1[4],vent1[5],date,id,vent1[6],vent1[7],vent1[8],vent1[9]);
                                Toast.makeText(Ventili.this,"Poruka Poslata",Toast.LENGTH_SHORT).show();
                            }

                            else if (poruka.matches("[1-3],[1-5];([T] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]) [DK] [0-9]{1,5};){1,5}")&&lit_min==1){
                                checkAndroidVersion();
                                dialog.cancel();
                                dbHalp.addVentH(Integer.toString(position),value,vent1[0],vent1[1],vent1[2],vent1[3],vent1[4],vent1[5],date,id,vent1[6],vent1[7],vent1[8],vent1[9]);
                                Toast.makeText(Ventili.this,"Poruka Poslata",Toast.LENGTH_SHORT).show();
                            }
                            else Toast.makeText(getApplicationContext(),"Neregularna konfiguracija",Toast.LENGTH_LONG).show();
                            }

                        if(position==1  ) {
                            vent21= edit1.getText().toString();vent22=edit2.getText().toString();vent23=edit3.getText().toString();
                            vent24= edit4.getText().toString();vent25=edit5.getText().toString();vent26=edit6.getText().toString();
                            vent27= edit7.getText().toString();vent28=edit8.getText().toString();vent29=edit9.getText().toString();vent210=edit10.getText().toString();
                            vent2=new String[]{vent21,vent22,vent23,vent24,vent25,vent26,vent27,vent28,vent29,vent210};
                            fixStr2();
                            int brint=brVent(vent2);
                            poruka=" ";
                            poruka=Integer.toString(position+1)+","+brint+";";
                            poruka+=format(lit_min,vent2);
                            vent2[1]=jesus(vent2[1]);vent2[3]=jesus(vent2[3]);vent2[5]=jesus(vent2[5]);vent2[7]=jesus(vent2[7]);vent2[9]=jesus(vent2[9]);

                            if(poruka.matches("[1-3],[1-5];([T] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]) [DK] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]);){1,5}")&&lit_min==0) {
                                checkAndroidVersion();
                                dialog.cancel();
                                dbHalp.addVentH(Integer.toString(position),value,vent2[0],vent2[1],vent2[2],vent2[3],vent2[4],vent2[5],date,id,vent2[6],vent2[7],vent2[8],vent2[9]);
                                Toast.makeText(Ventili.this,"Poruka Poslata",Toast.LENGTH_SHORT).show();
                            }
                            else if (poruka.matches("[1-3],[1-5];([T] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]) [DK] [0-9]{1,5};){1,5}")&&lit_min==1){
                                checkAndroidVersion();
                                dialog.cancel();
                                dbHalp.addVentH(Integer.toString(position),value,vent2[0],vent2[1],vent2[2],vent2[3],vent2[4],vent2[5],date,id,vent2[6],vent2[7],vent2[8],vent2[9]);
                                Toast.makeText(Ventili.this,"Poruka Poslata",Toast.LENGTH_SHORT).show();
                            }
                            else Toast.makeText(getApplicationContext(),"Neregularna konfiguracija",Toast.LENGTH_LONG).show();
                        }

                        if(position==2  ) {
                            vent31= edit1.getText().toString();vent32=edit2.getText().toString();vent33=edit3.getText().toString();
                            vent34= edit4.getText().toString();vent35=edit5.getText().toString();vent36=edit6.getText().toString();
                            vent37= edit7.getText().toString();vent38=edit8.getText().toString();vent39=edit9.getText().toString();vent310=edit10.getText().toString();
                            vent3=new String[]{vent31,vent32,vent33,vent34,vent35,vent36,vent37,vent38,vent39,vent310};
                            fixStr3();
                            int brint=brVent(vent3);
                            poruka=" ";
                            poruka=Integer.toString(position+1)+","+brint+";";
                            poruka+=format(lit_min,vent3);
                            vent3[1]=jesus(vent3[1]);vent3[3]=jesus(vent3[3]);vent3[5]=jesus(vent3[5]);vent3[7]=jesus(vent3[7]);vent3[9]=jesus(vent3[9]);

                            if(poruka.matches("[1-3],[1-5];([T] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]) [DK] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]);){1,5}")&&lit_min==0) {
                                checkAndroidVersion();
                                dialog.cancel();
                                dbHalp.addVentH(Integer.toString(position),value,vent3[0],vent3[1],vent3[2],vent3[3],vent3[4],vent3[5],date,id,vent3[6],vent3[7],vent3[8],vent3[9]);
                                Toast.makeText(Ventili.this,"Poruka Poslata",Toast.LENGTH_SHORT).show();
                            }
                           else if (poruka.matches("[1-3],[1-5];([T] (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9]) [DK] [0-9]{1,5};){1,5}")&&lit_min==1){
                                checkAndroidVersion();
                                dialog.cancel();
                                dbHalp.addVentH(Integer.toString(position),value,vent3[0],vent3[1],vent3[2],vent3[3],vent3[4],vent3[5],date,id,vent3[6],vent3[7],vent3[8],vent3[9]);
                                Toast.makeText(Ventili.this,"Poruka Poslata",Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Neregularna konfiguracija", Toast.LENGTH_LONG).show();



                            }
                        dbHalp.Del(id);
                        dbHalp.addVent(vent1[0],value,vent1[0],vent1[1],vent1[2],vent1[3],vent1[4],vent1[5],id,vent1[6],vent1[7],vent1[8],vent1[9]);
                        dbHalp.addVent(vent2[1],value,vent2[0],vent2[1],vent2[2],vent2[3],vent2[4],vent2[5],id,vent2[6],vent2[7],vent2[8],vent2[9]);
                        dbHalp.addVent(vent3[2],value,vent3[0],vent3[1],vent3[2],vent3[3],vent3[4],vent3[5],id,vent3[6],vent3[7],vent3[8],vent3[9]);







                        //FORMATIRANJE PORUKE

                        //poruka="test1";
                        //checkAndroidVersion();
                       // dialog.cancel();
                    }
                });
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });





    }
    public void showMessage (String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("CLOSE",null);
        builder.show();


    }

    public String jesus (String str1){
        if(!str1.isEmpty())
            str1+=isChecked;
        return str1;


    }

    public void checkAndroidVersion(){
        if (Build.VERSION.SDK_INT >= 13) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(Ventili.this, Manifest.permission.SEND_SMS);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Ventili.this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS);
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


    public class NewAdapter1 extends ArrayAdapter<String> {
        public int layout;
        public NewAdapter1(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            layout=resource;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewHolder= null;
            if (convertView== null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView= inflater.inflate(layout,parent,false);
                ViewHolder viewHolder=new ViewHolder();
                viewHolder.listText = (TextView) convertView.findViewById(R.id.ventlistText);
                viewHolder.listButton= (Button) convertView.findViewById(R.id.buttonVentOff);
                convertView.setTag(viewHolder);


            }

            mainViewHolder= (ViewHolder) convertView.getTag();

            mainViewHolder.listText.setText(getItem(position));
            mainViewHolder.listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                    poruka="test2";
                    checkAndroidVersion();

                }
            });



            return convertView;
        }

    }

    public int brVent(String [] x){
        int i=0;
        for (String p:x){
            if(!p.isEmpty())
            i++;}
        return i/2;
    }

    public String  format(int nac,String arr[]){
        String s,form1="";
        if (nac==1) s="K";
        else s="D";
        for(int i=0;i<=8;i+=2)
        if(!arr[i].isEmpty()|!arr[i+1].isEmpty())
            form1+="T"+" "+arr[i]+" "+s+" "+arr[i+1]+";";
            return form1;

    }

    public void fixStr(){
        int b = (lit_min ==0) ? 1 : 2; {
            for(int i=0;i<10;i+=b) {
                String[] parts = vent1[i].split(":");
                if (parts.length==2) {
                    if (parts[0].length() == 1)
                        parts[0] = 0 + parts[0];
                    if (parts[1].length() == 1)
                        parts[1] = 0 + parts[1];

                    vent1[i] = parts[0] + ":" + parts[1];
                }
            }
        }
    }
    public void fixStr3(){
        int b = (lit_min ==0) ? 1 : 2; {
            for(int i=0;i<10;i+=b) {
                String[] parts = vent3[i].split(":");
                if (parts.length==2) {
                    if (parts[0].length() == 1)
                        parts[0] = 0 + parts[0];
                    if (parts[1].length() == 1)
                        parts[1] = 0 + parts[1];

                    vent3[i] = parts[0] + ":" + parts[1];
                }
            }
        }
    }
    public void fixStr2(){
        int b = (lit_min ==0) ? 1 : 2; {
            for(int i=0;i<10;i+=b) {
                String[] parts = vent2[i].split(":");
                if (parts.length==2) {
                    if (parts[0].length() == 1)
                        parts[0] = 0 + parts[0];
                    if (parts[1].length() == 1)
                        parts[1] = 0 + parts[1];

                    vent2[i] = parts[0] + ":" + parts[1];
                }
            }
        }
    }

    public String focusHelp(String a){
        String []parts=new String[]{"",""};
        String re;
        if(a.contains(":")&&a.matches("[0-9]{1,2}:[0-9]{1,2}")) {
            parts = a.split(":");
            if(parts[0].length()==1)
                parts[0]="0"+parts[0];
            if(parts[1].length()==1)
                parts[1]=0+parts[1];
        }
        re=parts[0]+":"+parts[1];
        return re;
    }
    public View.OnFocusChangeListener  focusChange=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId()){
                case R.id.editT1:
                    String str= edit1.getText().toString();
                    String str1=focusHelp(str);
                    if(str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")){
                        edit1.setText(str1);
                        edit1.setBackgroundColor(Color.parseColor("#3300ff00"));
                    }
                    if((!edit1.getText().toString().isEmpty())&&!edit1.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) edit1.setBackgroundColor(Color.parseColor("#33ff0000"));
                    break;
                case R.id.editT2:
                    str=edit2.getText().toString();
                    str1=focusHelp(str);
                    if(lit_min==0) {
                        if (str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) {
                            edit2.setText(str1);
                            edit2.setBackgroundColor(Color.parseColor("#3300ff00"));
                        }
                        if (!edit1.getText().toString().isEmpty() && edit2.getText().toString().isEmpty())
                            edit2.setBackgroundColor(Color.parseColor("#33ff0000"));

                        if (!edit2.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")&&!edit2.getText().toString().isEmpty())
                            edit2.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    if(lit_min==1){
                       if( edit2.getText().toString().matches("[0-9]{1,5}"))
                        edit2.setBackgroundColor(Color.parseColor("#3300ff00"));
                        if (!edit1.getText().toString().isEmpty() && edit2.getText().toString().isEmpty())
                            edit2.setBackgroundColor(Color.parseColor("#33ff0000"));
                        if (!edit2.getText().toString().matches("[0-9]{1,5}")&&!edit2.getText().toString().isEmpty())
                            edit2.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    break;
                case R.id.editT3:
                    str=edit3.getText().toString();
                    str1=focusHelp(str);
                    if(str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")){
                        edit3.setText(str1);
                        edit3.setBackgroundColor(Color.parseColor("#3300ff00"));
                    }
                    if((!edit3.getText().toString().isEmpty())&&!edit3.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) edit3.setBackgroundColor(Color.parseColor("#33ff0000"));
                    break;
                case R.id.editT4:
                    str=edit4.getText().toString();
                    str1=focusHelp(str);
                    if(lit_min==0) {
                        if (str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) {
                            edit4.setText(str1);
                            edit4.setBackgroundColor(Color.parseColor("#3300ff00"));
                        }
                        if (!edit3.getText().toString().isEmpty() && edit4.getText().toString().isEmpty())
                            edit4.setBackgroundColor(Color.parseColor("#33ff0000"));

                        if (!edit4.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")&&!edit4.getText().toString().isEmpty())
                            edit4.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    if(lit_min==1){
                        if( edit4.getText().toString().matches("[0-9]{1,5}"))
                            edit4.setBackgroundColor(Color.parseColor("#3300ff00"));
                        if (!edit3.getText().toString().isEmpty() && edit4.getText().toString().isEmpty())
                            edit4.setBackgroundColor(Color.parseColor("#33ff0000"));
                        if (!edit4.getText().toString().matches("[0-9]{1,5}")&&!edit4.getText().toString().isEmpty())
                            edit4.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                case R.id.editT5:
                    str=edit5.getText().toString();
                    str1=focusHelp(str);
                    if(str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")){
                        edit5.setText(str1);
                        edit5.setBackgroundColor(Color.parseColor("#3300ff00"));
                    }
                    if((!edit5.getText().toString().isEmpty())&&!edit5.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) edit5.setBackgroundColor(Color.parseColor("#33ff0000"));
                    break;
                case R.id.editT6:
                    str=edit6.getText().toString();
                    str1=focusHelp(str);
                    if(lit_min==0) {
                        if (str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) {
                            edit6.setText(str1);
                            edit6.setBackgroundColor(Color.parseColor("#3300ff00"));
                        }
                        if (!edit5.getText().toString().isEmpty() && edit6.getText().toString().isEmpty())
                            edit6.setBackgroundColor(Color.parseColor("#33ff0000"));

                        if (!edit6.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")&&!edit6.getText().toString().isEmpty())
                            edit6.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    if(lit_min==1){
                        if( edit6.getText().toString().matches("[0-9]{1,5}"))
                            edit6.setBackgroundColor(Color.parseColor("#3300ff00"));
                        if (!edit5.getText().toString().isEmpty() && edit6.getText().toString().isEmpty())
                            edit6.setBackgroundColor(Color.parseColor("#33ff0000"));
                        if (!edit6.getText().toString().matches("[0-9]{1,5}")&&!edit6.getText().toString().isEmpty())
                            edit6.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    break;
                case R.id.editT7:
                    str=edit7.getText().toString();
                    str1=focusHelp(str);
                    if(str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")){
                        edit7.setText(str1);
                        edit7.setBackgroundColor(Color.parseColor("#3300ff00"));
                    }
                    if((!edit7.getText().toString().isEmpty())&&!edit7.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) edit7.setBackgroundColor(Color.parseColor("#33ff0000"));
                    break;
                case R.id.editT8:
                    str=edit8.getText().toString();
                    str1=focusHelp(str);
                    if(lit_min==0) {
                        if (str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) {
                            edit8.setText(str1);
                            edit8.setBackgroundColor(Color.parseColor("#3300ff00"));
                        }
                        if (!edit7.getText().toString().isEmpty() && edit8.getText().toString().isEmpty())
                            edit8.setBackgroundColor(Color.parseColor("#33ff0000"));

                        if (!edit8.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")&&!edit8.getText().toString().isEmpty())
                            edit8.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    if(lit_min==1){
                        if( edit8.getText().toString().matches("[0-9]{1,5}"))
                            edit8.setBackgroundColor(Color.parseColor("#3300ff00"));
                        if (!edit7.getText().toString().isEmpty() && edit8.getText().toString().isEmpty())
                            edit8.setBackgroundColor(Color.parseColor("#33ff0000"));
                        if (!edit8.getText().toString().matches("[0-9]{1,5}")&&!edit8.getText().toString().isEmpty())
                            edit8.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    break;
                case R.id.editT9:
                    str=edit9.getText().toString();
                    str1=focusHelp(str);
                    if(str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")){
                        edit9.setText(str1);
                        edit9.setBackgroundColor(Color.parseColor("#3300ff00"));
                    }
                    if((!edit9.getText().toString().isEmpty())&&!edit9.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) edit9.setBackgroundColor(Color.parseColor("#33ff0000"));
                    break;
                case R.id.editT10:
                    str=edit10.getText().toString();
                    str1=focusHelp(str);
                    if(lit_min==0) {
                        if (str1.matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")) {
                            edit10.setText(str1);
                            edit10.setBackgroundColor(Color.parseColor("#3300ff00"));
                        }
                        if (!edit9.getText().toString().isEmpty() && edit10.getText().toString().isEmpty())
                            edit10.setBackgroundColor(Color.parseColor("#33ff0000"));

                        if (!edit10.getText().toString().matches("(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])")&&!edit10.getText().toString().isEmpty())
                            edit10.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    if(lit_min==1){
                        if( edit10.getText().toString().matches("[0-9]{1,5}"))
                            edit10.setBackgroundColor(Color.parseColor("#3300ff00"));
                        if (!edit9.getText().toString().isEmpty() && edit10.getText().toString().isEmpty())
                            edit10.setBackgroundColor(Color.parseColor("#33ff0000"));
                        if (!edit10.getText().toString().matches("[0-9]{1,5}")&&!edit10.getText().toString().isEmpty())
                            edit10.setBackgroundColor(Color.parseColor("#33ff0000"));
                    }
                    break;

            }
        }
    };


}
