package com.android.dailydoze.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.dailydoze.Model.DataListModel;
import com.android.dailydoze.Model.ViewHolder;
import com.android.dailydoze.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    public LayoutInflater layoutInflater;
    public ArrayList<DataListModel> listStorage;
    Context mContext;

    public ListAdapter(Context context, ArrayList<DataListModel> customizedListView) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
        mContext = context;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public DataListModel getItem(int position) {
        return listStorage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder listViewHolder;
        if (convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
            listViewHolder.textInListView = convertView.findViewById(R.id.list_text);
            listViewHolder.imageInListView = convertView.findViewById(R.id.list_icon);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }

        listViewHolder.textInListView.setText(listStorage.get(position).text());
        listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).icon());
        return convertView;
    }
}
