package com.courseproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.courseproject.R;
import com.courseproject.model.Mark;

import java.util.List;

public class AdapterMark extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    List<Mark> markList;

    public AdapterMark(Context ctx, List<Mark> marks) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        markList = marks;
    }

    @Override
    public int getCount() {
        return markList.size();
    }

    @Override
    public Object getItem(int position) {
        return markList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            view = lInflater.inflate(R.layout.iteam_list_view_mark, parent, false);
            holder.subject = (TextView) view.findViewById(R.id.subject);
            holder.markSubect = (TextView) view.findViewById(R.id.mark_subject);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        if (position % 2 == 0)
            view.setBackgroundColor(0xFFE6E6E6);
        else
            view.setBackgroundColor(0xFFE8E8E8);
        Mark obj = getItems(position);
        holder.subject.setText(obj.getIdSubject()+"");
        holder.markSubect.setText(obj.getMark());
        return view;
    }

    public Mark getItems(int position) {
        return ((Mark) markList.get(position));
    }

    static class ViewHolder {
        public TextView subject;
        public TextView markSubect;
    }
}
