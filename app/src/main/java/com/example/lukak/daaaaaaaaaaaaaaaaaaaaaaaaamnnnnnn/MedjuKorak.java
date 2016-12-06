package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

public class MedjuKorak extends AppCompatActivity {
    String[] Opcije={"Podesi Ventile","Promijeni Ime ili Broj","Konfiguriši Uređjaj","Izbriši Uređjaj","Trenutno Stanje"};
    String value,valueBr,poz;
    int id;
    MyDBHalper halp;
    ActionBar ab;
    MyAdapter1 kalu;
    public ArrayList<String> data1=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medju_korak);
         ab=getSupportActionBar();
        data1.add(Opcije[0]);data1.add(Opcije[1]);data1.add(Opcije[2]);data1.add(Opcije[3]);data1.add(Opcije[4]);
        kalu= new MyAdapter1(this,R.layout.lista_opcija,data1);
        ListView listaOpcija= (ListView) findViewById(R.id.listaOpcija);
        listaOpcija.setAdapter(kalu);
        halp= new MyDBHalper(this,null,null,1);
        Intent intent = getIntent();
        value = intent.getStringExtra("key");
        valueBr=intent.getStringExtra("key1");
        poz=intent.getStringExtra("poz");
        id=intent.getIntExtra("id",id);
        Toast.makeText(getApplicationContext(),Integer.toString(id),Toast.LENGTH_SHORT).show();
        ab.setSubtitle(value);
        listaOpcija.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                if (position==0){
                    Intent myIntent = new Intent(MedjuKorak.this, Ventili.class);
                    myIntent.putExtra("key",value);
                    myIntent.putExtra("key1",valueBr);
                    myIntent.putExtra("id",id);
                    MedjuKorak.this.startActivity(myIntent);
                }
                if (position == 1){
                    final Dialog dialog = new Dialog(MedjuKorak.this);
                    dialog.setContentView(R.layout.dialog_layout);
                    Window window=dialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    final EditText editIme= (EditText) dialog.findViewById(R.id.editIme);
                    final EditText editBroj= (EditText) dialog.findViewById(R.id.edittBroj);
                    Button btn1= (Button) dialog.findViewById(R.id.btn1);
                    Button btn2= (Button) dialog.findViewById(R.id.btn2);

                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String str=editIme.getText().toString();
                            String br=editBroj.getText().toString();
                            Intent i = new Intent();
                            if(!str.isEmpty()){
                            i.putExtra("key1",str);
                            i.putExtra("poz",poz);
                             halp.getWritableDatabase().execSQL("UPDATE kaluTable SET name= " +" ' " +str+" ' " + " WHERE _id= "+" ' "+ id +" ' ");
                             value=str;
                             ab.setSubtitle(value);
                            setResult(RESULT_OK,i);}

                            if(!br.isEmpty()){
                                i.putExtra("key2",br);
                                i.putExtra("poz",poz);
                                halp.getWritableDatabase().execSQL("UPDATE kaluTable SET brojUredjaja= " +" ' " +br+" ' " + " WHERE _id= "+" ' "+ id +" ' ");
                                valueBr=br;
                                setResult(RESULT_OK,i);}



                            dialog.cancel();

                        }
                    });
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                }

                if(position==3){
                    final Dialog confirmdel=new Dialog(MedjuKorak.this);
                    confirmdel.setContentView(R.layout.confirmdeletedialog);
                    Window window=confirmdel.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    confirmdel.show();
                    Button delOk= (Button) confirmdel.findViewById(R.id.okdelete);
                    Button delCancel= (Button) confirmdel.findViewById(R.id.canceldelete);

                    delOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent();
                            i.putExtra("delete",Integer.parseInt(poz));
                            setResult(2,i);
                            halp.getWritableDatabase().execSQL("DELETE FROM kaluTable  " + " WHERE _id= "+" ' "+ id +" ' ");
                            confirmdel.cancel();
                            finish();

                        }
                    });

                  delCancel.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          confirmdel.cancel();
                      }
                  });



                }
                if(position==2){
                    Intent konfIntent = new Intent(MedjuKorak.this, Konfiguracija.class);
                    konfIntent.putExtra("id",id);
                    konfIntent.putExtra("valbr",valueBr);
                    MedjuKorak.this.startActivity(konfIntent);

                }
                if(position==4){
                    Intent stat=new Intent(MedjuKorak.this,ScrollingActivity.class);
                    stat.putExtra("id",id);
                    MedjuKorak.this.startActivity(stat);

                }

            }
        });

    }
    public class MyAdapter1 extends ArrayAdapter<String > {
        public int layout;
        public MyAdapter1(Context context, int resource, List<String> objects) {
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
                viewHolder.listText = (TextView) convertView.findViewById(R.id.listTextOpcija);
                convertView.setTag(viewHolder);


            }

            mainViewHolder= (ViewHolder) convertView.getTag();
            mainViewHolder.listText.setText(getItem(position));




            return convertView;
        }
    }

}
