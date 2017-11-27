package com.courseproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.courseproject.R;
import com.courseproject.model.mark.Mark;

import java.util.List;

public class AdapterMark extends BaseAdapter { // адаптер для вывода списка в компоненту listView для списка оценок на экране экзамены, наследуемся от стандартного адаптра

    Context ctx;
    LayoutInflater lInflater;
    List<Mark> markList;

    public AdapterMark(Context ctx, List<Mark> marks) { // конструктор, устонавыливаем значения на все поля
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
        ViewHolder holder = new ViewHolder(); // инициализируем холдер
        if (view == null) {
            view = lInflater.inflate(R.layout.iteam_list_view_mark, parent, false);
            holder.subject = (TextView) view.findViewById(R.id.subject);
            holder.markSubect = (TextView) view.findViewById(R.id.mark_subject);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        Mark obj = getItems(position);
        boolean flag = false; // смотрим оценку и выводим в необходимом цветовом оттенке
        if (obj.getMark().equals("не допуск") || obj.getMark().equals("не зачет"))
        {
            view.setBackgroundColor(Color.RED);
            holder.subject.setTextColor(Color.WHITE);
            holder.subject.setTextSize(20);
            holder.markSubect.setTextColor(Color.WHITE);
            holder.markSubect.setTextSize(20);
            flag = true;
        }
        if (obj.getMark().equals("зачёт"))
        {
            view.setBackgroundColor(Color.GREEN);
            holder.subject.setTextColor(Color.BLACK);
            holder.subject.setTextSize(10);
            holder.markSubect.setTextColor(Color.BLACK);
            holder.markSubect.setTextSize(10);
            flag = true;
        }
        try {
            if (Integer.parseInt(obj.getMark()) < 4) {
                view.setBackgroundColor(Color.RED);
                holder.subject.setTextColor(Color.WHITE);
                holder.subject.setTextSize(20);
                holder.markSubect.setTextColor(Color.WHITE);
                holder.markSubect.setTextSize(20);
                flag = true;
            }
        }// обрабатываем исключения, если оценка у нас в виде текста( зачет, не зачет, не допуск или просто пустое значение)
        catch (NumberFormatException e)
        {}
        try {
            if (Integer.parseInt(obj.getMark()) > 3) {
                view.setBackgroundColor(Color.GREEN);
                holder.subject.setTextColor(Color.BLACK);
                holder.subject.setTextSize(10);
                holder.markSubect.setTextColor(Color.BLACK);
                holder.markSubect.setTextSize(10);
                flag = true;
            }
        }// обрабатываем исключения, если оценка у нас в виде текста( зачет, не зачет, не допуск или просто пустое значение)
        catch (NumberFormatException e)
        {}// если нечего подошло ставим стандартные цветовые оттенки
        if(!flag)
        {
            view.setBackgroundColor(0xFFE8E8E8);
            holder.subject.setTextColor(Color.BLACK);
            holder.subject.setTextSize(10);
            holder.markSubect.setTextColor(Color.BLACK);
            holder.markSubect.setTextSize(10);
        }

        // выстовляем значения
        holder.subject.setText(obj.getSubject().getName());
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
