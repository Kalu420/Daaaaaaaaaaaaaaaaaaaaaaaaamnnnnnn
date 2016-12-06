package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyDBHalper bl;
    Button addButton;
    MyAdapter kalu;
    ToggleButton tog;
    ListView lv;
    int REQUEST_CODE=1,ID,brisi,k;
    public ArrayList<String> dataa=new ArrayList<String>();
    public ArrayList <CustomObject> objekti=new ArrayList<CustomObject>();
    public ArrayList<String> arrayBrojevi=new ArrayList<String>();
    String brTel;
    private static final int REQUEST_RECEIVE_SMS = 3;
   long p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissionToReceiveSMS();
        addButton = (Button) findViewById(R.id.addButton);
        bl= new MyDBHalper(this,null,null,1);
         lv =(ListView) findViewById(R.id.listV1);
        viewShit();
        kalu=new MyAdapter(this,R.layout.list_item,objekti);
        lv.setAdapter(kalu);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Toast.makeText(MainActivity.this, "List Item " + position, Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, MedjuKorak.class);
                String prenos=objekti.get(position).getProp1();
                myIntent.putExtra("key",prenos);
                String prenosBroja=arrayBrojevi.get(position);
                myIntent.putExtra("key1",prenosBroja);
                String poz=Integer.toString(position);
                myIntent.putExtra("poz",poz);
                Cursor c=bl.getID(prenos);
                while (c.moveToNext())
                ID=c.getInt(0);
                myIntent.putExtra("id",ID);
                MainActivity.this.startActivityForResult(myIntent,REQUEST_CODE);


            }
        });


    }

    public void addButtonClicked (View V){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_layout);
        Window window=dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        final EditText editIme= (EditText) dialog.findViewById(R.id.editIme);
        final EditText editBroj= (EditText) dialog.findViewById(R.id.edittBroj);
       // tog= (ToggleButton) dialog.findViewById(R.id.toggle_litri_min);
        Button btn1= (Button) dialog.findViewById(R.id.btn1);
        Button btn2= (Button) dialog.findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String str=editIme.getText().toString();
             String br=editBroj.getText().toString();


             if(!dataa.contains(str)&&!arrayBrojevi.contains(br)) {
                 //if(tog.isChecked())
                    p = bl.Addd(str,br,"min");
                 //if (!tog.isChecked())
                    // p = bl.Addd(str,br,"lit");
                 dataa.add(str);
                 arrayBrojevi.add(br);
                 objekti.add(new CustomObject(str,br));
                 bl.AddConf();

             }
                 else Toast.makeText(MainActivity.this, "Allready exists",Toast.LENGTH_SHORT).show();
                 kalu.notifyDataSetChanged();

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


    public void viewShit(){
        Cursor res = bl.getData();
        while (res.moveToNext()){
            dataa.add(res.getString(1));
            arrayBrojevi.add(res.getString(2));
            objekti.add(new CustomObject(res.getString(1),res.getString(2)));
        }


    }

    public class MyAdapter extends ArrayAdapter<CustomObject > {
        public int layout;
        public MyAdapter(Context context, int resource, List<CustomObject> objects) {
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
                viewHolder.listText = (TextView) convertView.findViewById(R.id.listText);
                viewHolder.listButton= (Button) convertView.findViewById(R.id.listButton);
                viewHolder.listText1 = (TextView) convertView.findViewById(R.id.listText1);
                convertView.setTag(viewHolder);


            }

            mainViewHolder= (ViewHolder) convertView.getTag();
            mainViewHolder.listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, History.class);
                    String prenos=objekti.get(position).getProp1();
                    myIntent.putExtra("key",prenos);
                    Cursor c=bl.getID(prenos);
                    while (c.moveToNext())
                        ID=c.getInt(0);
                    myIntent.putExtra("id",ID);
                    MainActivity.this.startActivity(myIntent);

                }
            });
            mainViewHolder.listText.setText(getItem(position).getProp1());
            mainViewHolder.listText1.setText(getItem(position).getProp2());



            return convertView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                String result =data.getStringExtra("key1");
                String result1=data.getStringExtra("key2");
                String poz=data.getStringExtra("poz");
                if(result!=null){
                    dataa.set(Integer.parseInt(poz),result);objekti.get(Integer.parseInt(poz)).setProp1(result); }
                if(result1!=null){
                    arrayBrojevi.set(Integer.parseInt(poz),result1);objekti.get(Integer.parseInt(poz)).setProp2(result1);}


                kalu.notifyDataSetChanged();

            }
            if(resultCode==2){
                brisi=data.getIntExtra("delete",brisi);
                dataa.remove(brisi);
                arrayBrojevi.remove(brisi);
                objekti.remove(brisi);
                kalu.notifyDataSetChanged();




            }

        }
    }
    @TargetApi(Build.VERSION_CODES.M)
    public void getPermissionToReceiveSMS() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS)) {
                    // Show our own UI to explain to the user why we need to read the contacts
                    // before actually requesting the permission and showing the default UI
                }
            }

            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},REQUEST_RECEIVE_SMS);
        }
    }

}
