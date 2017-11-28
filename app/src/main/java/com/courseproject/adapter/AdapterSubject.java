package com.courseproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.courseproject.R;
import com.courseproject.model.schedules.Schedule;

import java.util.List;


public class AdapterSubject extends BaseAdapter {// адаптер для вывода списка в компоненту listView предметов для рассписания, наследуемся от стандартного адаптра

    private LayoutInflater lInflater;
    private List<Schedule> scheduleList;

    public AdapterSubject(Context ctx, List<Schedule> schedules) {
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        scheduleList = schedules;
    }

    @Override
    public int getCount() {
        return scheduleList.size();
    }

    @Override
    public Object getItem(int position) {
        return scheduleList.get(position);
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
            view = lInflater.inflate(R.layout.iteam_subject, parent, false);
            holder.subject = view.findViewById(R.id.nameSubject);
            holder.start = view.findViewById(R.id.start);
            holder.finish = view.findViewById(R.id.finish);
            holder.auditory = view.findViewById(R.id.auditory);
            holder.colorBlok = view.findViewById(R.id.colorSubject);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        Schedule obj = getItems(position);

        holder.subject.setText(obj.getSubject());
        holder.start.setText(obj.getStartLessonTime());
        holder.finish.setText(obj.getEndLessonTime());
        holder.auditory.setText(obj.getAuditory().get(0));
        // выстовляем цвет блока в зависемости от типа пары
        if(obj.getLessonType().equals("ЛК"))
        {
            holder.colorBlok.setBackgroundColor(Color.GREEN);
        }
        if(obj.getLessonType().equals("ПЗ"))
            holder.colorBlok.setBackgroundColor(Color.YELLOW);
        if(obj.getLessonType().equals("ЛР"))
            holder.colorBlok.setBackgroundColor(Color.RED);
        else
            holder.colorBlok.setBackgroundColor(Color.BLUE);
        return view;
    }

    public Schedule getItems(int position) {
        return scheduleList.get(position);
    }

    static class ViewHolder {
        public TextView subject;
        public TextView start;
        public TextView finish;
        public TextView auditory;
        public TextView colorBlok;
    }
}

