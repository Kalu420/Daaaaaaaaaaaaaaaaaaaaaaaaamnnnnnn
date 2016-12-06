package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lukak on 8/28/2016.
 */
public class AdapterZaExpList extends BaseExpandableListAdapter {

    public List<String> headerTitles;
    public List<String> orig;
    public HashMap<String,List<String>> childTitles;
    public Context ctx;

    public AdapterZaExpList(Context ctx,List<String> headerTitles,HashMap<String, List<String>> childTitles) {
        this.childTitles = childTitles;
        this.ctx=ctx;
        this.headerTitles=headerTitles;
    }

    @Override
    public int getGroupCount() {
        return headerTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childTitles.get(headerTitles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerTitles.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  childTitles.get(headerTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int i, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String title=(String) this.getGroup(groupPosition);
        if(convertView ==null){
            LayoutInflater inflater= (LayoutInflater)this.ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.datumrow,null);

        }
        TextView textView= (TextView) convertView.findViewById(R.id.dateText);
        textView.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String title= (String) this.getChild(groupPosition,childPosition);

        if (convertView == null){
            LayoutInflater inflater= (LayoutInflater)this.ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.ventrow,null);

        }

        TextView textView= (TextView) convertView.findViewById(R.id.ventHist);
        textView.setText(title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }



    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<String> results = new ArrayList<String>();
                if(orig==null)
                    orig=headerTitles;
                if(constraint !=null){
                    if(orig !=null&&orig.size()>0){
                        for(final String g:orig){
                            if(g.toLowerCase().contains(constraint.toString().toLowerCase()))
                                results.add(g);
                        }

                    }
                    oReturn.values=results;

                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                headerTitles=(ArrayList<String> ) results.values;
                notifyDataSetChanged();
            }
        };
    }
}

