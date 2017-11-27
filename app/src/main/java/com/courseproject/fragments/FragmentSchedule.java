package com.courseproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.courseproject.R;
import com.courseproject.adapter.AdapterSubject;
import com.courseproject.constants.ConstantsForIntent;
import com.courseproject.dao.ScheduleDataBaseHadler;
import com.courseproject.dao.StudentDataBaseHadler;
import com.courseproject.model.schedules.DayClass;
import com.courseproject.model.student.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
// фрагмент для отображения расписания студента
public class FragmentSchedule extends android.support.v4.app.Fragment {

    private static final int LAYOUT = R.layout.fragment_schedule;
    private View view;

    String[] stringDay = {"Воскресенье","Понедельник","Вторник","Среда","Четверг","Пятница","Суббота"};// список дней недели, первым днем считается воскресение
    String[] week = {"1","2","3","4"};// список номеров недели

    AdapterSubject adapterSubject;
    ListView listView;

    final Date date = new Date();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        Bundle bundle = this.getArguments();
        List<DayClass> schedules = new ArrayList<>();
        final Student student = new StudentDataBaseHadler(getContext())
                .getById(bundle.getLong(ConstantsForIntent.idStudent));// получения инфы о студенте из БД
        schedules = new ScheduleDataBaseHadler(getContext()).getSchedule(student.getGroup().getId(),
                stringDay[date.getDay()],getNumberstudyweek());// получения инфы о сегодняшнем расписании
        try {
            adapterSubject = new AdapterSubject(getActivity(),schedules.get(0).getSchedule());
            listView = (ListView)view.findViewById(R.id.listSchedule);
            listView.setAdapter(adapterSubject);
        }// если сегодня нету пар, масив будет пустым, перехватываем это с помощью исключения и выводим сообщения об выходном
        catch (IndexOutOfBoundsException e)
        {
            Toast.makeText(getContext(), "Выходной", Toast.LENGTH_SHORT).show();
        }
        //выподающий список для дней недели
        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(getContext(), R.layout.my_spinner_iteam, stringDay);// создаем адаптер
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerDay = (Spinner) view.findViewById(R.id.day);// указваем где находится выподающий список
        spinnerDay.setAdapter(adapterDay);// указываем адаптер
        spinnerDay.setSelection(date.getDay());// текущий день
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//обработчик нажания
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    AdapterSubject adapterSubjectNew = new AdapterSubject(getActivity(),(new ScheduleDataBaseHadler(getContext()).getSchedule(student.getGroup().getId(),
                            stringDay[position],getNumberstudyweek())).get(0).getSchedule()); // создаем адаптер для отображения реузльатов
                    listView.setAdapter(adapterSubjectNew);
                }// если сегодня нету пар, масив будет пустым, перехватываем это с помощью исключения и выводим сообщения об выходном
                catch (IndexOutOfBoundsException e)
                {
                    Toast.makeText(getContext(), "Выходной", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //выподающий список для номера недели, смотри выше
        ArrayAdapter<String> adapterWeek = new ArrayAdapter<String>(getContext(), R.layout.my_spinner_iteam, week);
        adapterWeek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerWeek = (Spinner) view.findViewById(R.id.week);
        spinnerWeek.setAdapter(adapterWeek);
        spinnerWeek.setSelection(getNumberstudyweek() - 1);
        spinnerWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    AdapterSubject adapterSubjectNew = new AdapterSubject(getActivity(),
                            (new ScheduleDataBaseHadler(getContext()).getSchedule(student.getGroup().getId(),
                                    stringDay[date.getDay()],position + 1)).get(0).getSchedule());
                    listView.setAdapter(adapterSubjectNew);
                }
                catch (IndexOutOfBoundsException e)
                {
                    Toast.makeText(getContext(), "Выходной", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        return view;
    }
    // метод для определения текущей учебной недели, 1 неделяй считается та, когда было 1 сентября
    private int getNumberstudyweek()
    {
        Calendar c = Calendar.getInstance();
        int  now_number_week = c.get(Calendar.WEEK_OF_YEAR);
        c.set(c.get(Calendar.YEAR),8,1);
        int week_1_semtember = c.get(Calendar.WEEK_OF_YEAR);
        if(now_number_week > week_1_semtember)// if the date from September 1 to December 31
            return (now_number_week - week_1_semtember) % 4 + 1;
        else
        {
            c.set(c.get(Calendar.YEAR) - 1,8,1);
            week_1_semtember = c.get(Calendar.WEEK_OF_YEAR);
            int day = 31;
            int col_week_in_year = 1;
            do {
                c.set(c.get(Calendar.YEAR) - 1,11,day);
                col_week_in_year = c.get(Calendar.WEEK_OF_YEAR);
                day--;
            }
            while (col_week_in_year == 1);
            return (now_number_week + col_week_in_year - week_1_semtember) % 4 + 1;
        }
    }
}