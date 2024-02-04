package com.android.dailydoze.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.android.dailydoze.Model.FAQItem;
import com.android.dailydoze.R;

import java.util.List;

public class FAQAdapter extends BaseAdapter {

    private Context context;
    private List<FAQItem> faqItems;

    public FAQAdapter(Context context, List<FAQItem> faqItems) {
        this.context = context;
        this.faqItems = faqItems;
    }

    @Override
    public int getCount() {
        return faqItems.size();
    }

    @Override
    public Object getItem(int position) {
        return faqItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.faq_item, parent, false);
            holder = new ViewHolder();
            holder.questionTextView = convertView.findViewById(R.id.faq_question);
            holder.answerTextView = convertView.findViewById(R.id.faq_answer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FAQItem item = faqItems.get(position);
        holder.questionTextView.setText(item.getQuestion());
        holder.answerTextView.setText(item.getAnswer());

        return convertView;
    }

    private static class ViewHolder {
        TextView questionTextView;
        TextView answerTextView;
    }
}
