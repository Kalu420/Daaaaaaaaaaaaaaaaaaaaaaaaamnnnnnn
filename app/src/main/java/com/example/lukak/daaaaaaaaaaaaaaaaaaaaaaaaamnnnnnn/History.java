package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class History extends AppCompatActivity {
    ExpandableListView expandableListView;
    String value;
    int id;
    List<String> headings,L1,L2,L3;
    HashMap<String,List<String>> childList=new HashMap<String,List<String>>();
    String  [] headingItems,l1,l2,l3;
    AdapterZaExpList myAdapter;
    SearchView sv;
    MyDBHalper dbHalp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbHalp= new MyDBHalper (this,null,null,1);
        sv= (SearchView) findViewById(R.id.searchView);
        Intent intent = getIntent();
        value = intent.getStringExtra("key");
        id=intent.getIntExtra("id",id);
        Toast.makeText(getApplicationContext(),Integer.toString(id),Toast.LENGTH_SHORT).show();
        ActionBar ab= getSupportActionBar();
        ab.setSubtitle(value);
        expandableListView=(ExpandableListView) findViewById(R.id.expList);
        L1 = new ArrayList<String>();
        L2 = new ArrayList<String>();
        L3 = new ArrayList<String>();
        headings = new ArrayList<String>();
        headingItems = new String[]{"Blbl","Wtf","Aafuq"};
        l1 = new String[]{"Ventil 1","Ventil 2","Ventil 3"};
        //for(String title:headingItems)
            //headings.add(title);
        setHeadings();
        for(String title:l1)
            L1.add(title);
        for(String bl :headings)
        childList.put(bl,L1);
       // childList.put(headings.get(1),L1);
       // childList.put(headings.get(2),L1);
        myAdapter = new AdapterZaExpList(this,headings,childList);
        expandableListView.setAdapter(myAdapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int grpPos, int childPos, long l) {

                final String date = myAdapter.getGroup(grpPos).toString();

               Toast.makeText(getApplicationContext(), "child clicked  "+myAdapter.getChild(grpPos,childPos)+" parent " +myAdapter.getGroup(grpPos), Toast.LENGTH_SHORT).show();
                Cursor res = dbHalp.getHist(id,date,Integer.toString(childPos));
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    if(!res.getString(3).toString().isEmpty()) {
                        buffer.append("Paljenje: " + res.getString(3) + "  ");
                        buffer.append("Trajanje: " + res.getString(4) + "\n");
                    }
                    if(!res.getString(5).toString().isEmpty()) {
                        buffer.append("Paljenje: " + res.getString(5) + "  ");
                        buffer.append("Trajanje: " + res.getString(6) + "\n");
                    }
                    if(!res.getString(7).toString().isEmpty()) {
                        buffer.append("Paljenje: " + res.getString(7) + "  ");
                        buffer.append("Trajanje: " + res.getString(8) + "\n");
                    }

                    if(!res.getString(11).toString().isEmpty()) {
                        buffer.append("Paljenje: " + res.getString(11) + "  ");
                        buffer.append("Trajanje: " + res.getString(12) + "\n");
                    }
                    if(!res.getString(13).toString().isEmpty()) {
                        buffer.append("Paljenje: " + res.getString(13) + "  ");
                        buffer.append("Trajanje: " + res.getString(14) + "\n");
                    }
                }
                showMessage("Istorija",buffer.toString());

                return false;

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

    public void setHeadings(){
        Cursor res = dbHalp.getHead(id);
        while (res.moveToNext())
            headings.add(res.getString(0));

    }

}
