package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lukak on 8/19/2016.
 */
public class MyAdapter extends ArrayAdapter <String > {
    public int layout;
    public MyAdapter(Context context, int resource, List<String> objects) {
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
            convertView.setTag(viewHolder);


        }

        mainViewHolder= (ViewHolder) convertView.getTag();
        mainViewHolder.listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();

            }
        });
        mainViewHolder.listText.setText(getItem(position));



        return convertView;
    }
}
