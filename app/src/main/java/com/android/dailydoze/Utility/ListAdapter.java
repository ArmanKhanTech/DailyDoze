package com.android.dailydoze.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.dailydoze.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    public LayoutInflater layoutInflater;
    public ArrayList<NotiList> listStorage;
    Context mContext;

    public ListAdapter(Context context, ArrayList<NotiList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
        mContext=context;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public NotiList getItem(int position) {
        return listStorage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.noti_list, parent, false);
            listViewHolder.textInListView = convertView.findViewById(R.id.noti_time);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(listStorage.get(position).getTime());

        return convertView;
    }
}
