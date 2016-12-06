package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lukak on 8/25/2016.
 */
public class NewAdapter extends ArrayAdapter<String> {
    public int layout;
    public NewAdapter(Context context, int resource, List<String> objects) {
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


        return convertView;
    }

}
