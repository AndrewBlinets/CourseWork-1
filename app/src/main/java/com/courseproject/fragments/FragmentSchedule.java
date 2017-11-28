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
import android.widget.TextView;

import com.courseproject.R;
import com.courseproject.adapter.AdapterSubject;
import com.courseproject.constants.ConstantsForIntent;
import com.courseproject.dao.ScheduleDataBaseHadler;
import com.courseproject.dao.StudentDataBaseHadler;
import com.courseproject.model.schedules.DayClass;
import com.courseproject.model.schedules.Schedule;
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
    TextView dayOff;

    final Date date = new Date();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        dayOff = view.findViewById(R.id.dayOff);
        Bundle bundle = this.getArguments();
        List<DayClass> schedules;
        final int[] numberDay = {date.getDay()};
        final int[] numberWeek = {getNumberstudyweek()};
        final Student student = new StudentDataBaseHadler(getContext())
                .getById(bundle.getLong(ConstantsForIntent.idStudent));// получения инфы о студенте из БД
        schedules = new ScheduleDataBaseHadler(getContext()).getSchedule(student.getGroup().getId(),
                stringDay[numberDay[0]], numberWeek[0]);// получения инфы о сегодняшнем расписании
        try {
            adapterSubject = new AdapterSubject(getActivity(),schedules.get(0).getSchedule());
            dayOff.setText("");
        }// если сегодня нету пар, масив будет пустым, перехватываем это с помощью исключения и выводим сообщения об выходном
        catch (IndexOutOfBoundsException e)
        {
            adapterSubject = new AdapterSubject(getActivity(),new ArrayList<Schedule>());
            dayOff.setText("Выходной");
        }
        listView = view.findViewById(R.id.listSchedule);
        listView.setAdapter(adapterSubject);
        //выподающий список для дней недели
        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(getContext(), R.layout.my_spinner_iteam, stringDay);// создаем адаптер
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerDay = view.findViewById(R.id.day);// указваем где находится выподающий список
        spinnerDay.setAdapter(adapterDay);// указываем адаптер
        spinnerDay.setSelection(numberDay[0]);// текущий день
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//обработчик нажания
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    AdapterSubject adapterSubjectNew = new AdapterSubject(getActivity(),(new ScheduleDataBaseHadler(getContext()).getSchedule(student.getGroup().getId(),
                            stringDay[position], numberWeek[0])).get(0).getSchedule()); // создаем адаптер для отображения реузльатов
                    listView.setAdapter(adapterSubjectNew);
                    dayOff.setText("");
                    numberDay[0] = position;
                }// если сегодня нету пар, масив будет пустым, перехватываем это с помощью исключения и выводим сообщения об выходном
                catch (IndexOutOfBoundsException e)
                {
                    AdapterSubject adapterSubjectNew = new AdapterSubject(getActivity(),new ArrayList<Schedule>()); // создаем адаптер для отображения реузльатов
                    listView.setAdapter(adapterSubjectNew);
                    dayOff.setText("Выходной");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //выподающий список для номера недели, смотри выше
        ArrayAdapter<String> adapterWeek = new ArrayAdapter<>(getContext(), R.layout.my_spinner_iteam, week);
        adapterWeek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerWeek = view.findViewById(R.id.week);
        spinnerWeek.setAdapter(adapterWeek);
        spinnerWeek.setSelection(numberWeek[0] - 1);
        spinnerWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    AdapterSubject adapterSubjectNew = new AdapterSubject(getActivity(),
                            (new ScheduleDataBaseHadler(getContext()).getSchedule(student.getGroup().getId(),
                                    stringDay[numberDay[0]],position + 1)).get(0).getSchedule());
                    numberWeek[0] = position + 1;
                    listView.setAdapter(adapterSubjectNew);
                    dayOff.setText("");
                }
                catch (IndexOutOfBoundsException e)
                {
                    AdapterSubject adapterSubjectNew = new AdapterSubject(getActivity(),new ArrayList<Schedule>()); // создаем адаптер для отображения реузльатов
                    listView.setAdapter(adapterSubjectNew);
                    dayOff.setText("Выходной");
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