package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Group;
import com.courseproject.model.Subject;
import com.courseproject.model.schedules.DayClass;
import com.courseproject.model.schedules.Schedule;

import java.util.List;


public class ScheduleDataBaseHadler extends BaseClassDataBaseHadler<DayClass>{

    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE_SCHEDULE + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE_SCHEDULE;
    private GroupDataBaseHadler groupDataBaseHadler;
    private SubjectDataBaseHadler subjectDataBaseHadler;

    public ScheduleDataBaseHadler(Context context) {
        super(context);
        groupDataBaseHadler = new GroupDataBaseHadler(context);
        subjectDataBaseHadler = new SubjectDataBaseHadler(context);
    }

    @Override
    public void add(DayClass dayClass) {

        List<Schedule> list = dayClass.getSchedule();
        for (Schedule schedule : list) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            long idGroup = 0, idSubject = 0;
            Group group = groupDataBaseHadler.getGroupByNamber(schedule.getStudentGroup().get(0));
            if (group == null) {
                groupDataBaseHadler.add(new Group(schedule.getStudentGroup().get(0), 1));
                idGroup = groupDataBaseHadler.getGroupByNamber(schedule.getStudentGroup().get(0)).getId();
            } else {
                idGroup = group.getId();
            }
            Subject subject = subjectDataBaseHadler.getByName(schedule.getSubject());
            if (subject == null) {
                subjectDataBaseHadler.add(new Subject(schedule.getSubject()));
                idSubject = subjectDataBaseHadler.getByName(schedule.getSubject()).getId();
            } else {
                idSubject = subject.getId();
            }

        values.put(KEY_NAME_DAY, dayClass.getWeekDay());
        values.put(KEY_WEEK, schedule.getWeekNumber().get(0));
        values.put(KEY_ID_GROUP, idGroup);
        try {
            values.put(KEY_AUDITORY, schedule.getAuditory().get(0));
        }
        catch (IndexOutOfBoundsException e)
        {
            values.put(KEY_AUDITORY, "");
        }
        values.put(KEY_START_LESSON, schedule.getStartLessonTime());
        values.put(KEY_FINISH_LESSON, schedule.getEndLessonTime());
        values.put(KEY_ID_SUBJECT,idSubject);
        values.put(KEY_LESSON_TYPE, schedule.getLessonType());
        db.insert(NAME_TABLE_SCHEDULE, null, values);
        db.close();
        }
    }

    /*
    * метод получения по id public Schedule getById(long id) не переопределяем, т.к. мы будем получать расписания по номеру группы, по дню и номере неделе.
    * метод получения всего расписания List<Schedule> getAll() не переопределяем, т.к. мы будем получать расписания по номеру группы, по дню и номере неделе.
    */

    @Override
    public int update(DayClass dayClass) {
        // TO DO
        return 0;
    }

    @Override
    public void deleteById(int id) {
        // TO DO
    }

    @Override
    public void deleteAll() {
        // TO DO
    }
}
