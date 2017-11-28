package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Group;
import com.courseproject.model.Subject;
import com.courseproject.model.schedules.DayClass;
import com.courseproject.model.schedules.Schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// класс для работы с расписанием в БД
public class ScheduleDataBaseHadler extends BaseClassDataBaseHadler<DayClass>{

    private String GET_SCHEDULE = SELECT_ALL + NAME_TABLE_SCHEDULE + " WHERE " + KEY_ID_GROUP + " = ? AND " +
            KEY_NAME_DAY + " = ? AND (" + KEY_WEEK + " = ? OR " + KEY_WEEK + " = 0)";
    private GroupDataBaseHadler groupDataBaseHadler;
    private SubjectDataBaseHadler subjectDataBaseHadler;

    public ScheduleDataBaseHadler(Context context) {
        super(context);
        groupDataBaseHadler = new GroupDataBaseHadler(context);
        subjectDataBaseHadler = new SubjectDataBaseHadler(context);
    }

    @Override
    public void add(DayClass dayClass) {// перопределенный метод
        List<Schedule> list = dayClass.getSchedule();
        for (Schedule schedule : list) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            long idGroup, idSubject;
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

    @Override
    public void deleteAll() {// перопределенный метод
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + NAME_TABLE_SCHEDULE);
    }

    public List<DayClass> getSchedule(long id, String dayName, int week) {// получения расписания по id группы, дню недели и номеру недели
        List<DayClass> schedule = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_SCHEDULE,  new String[]{String.valueOf(id), dayName, String.valueOf(week)});
        String day = "";
        int count = -1;
        if (cursor.moveToFirst()) {
            do {
                int weekDayIndex = cursor.getColumnIndex(KEY_NAME_DAY);
                int weekNumberIndex = cursor.getColumnIndex(KEY_WEEK);
                int idGroupIndex = cursor.getColumnIndex(KEY_ID_GROUP);
                int auditoryIndex = cursor.getColumnIndex(KEY_AUDITORY);
                int startLessomIndex = cursor.getColumnIndex(KEY_START_LESSON);
                int finishLessomIndex = cursor.getColumnIndex(KEY_FINISH_LESSON);
                int idSubjectIndex = cursor.getColumnIndex(KEY_ID_SUBJECT);
                int lessonTypeIndex = cursor.getColumnIndex(KEY_LESSON_TYPE);
                if(day.equals(cursor.getString(weekDayIndex)))
                {
                    addElement(schedule, cursor, count, weekNumberIndex, idGroupIndex, auditoryIndex, startLessomIndex, finishLessomIndex, idSubjectIndex, lessonTypeIndex);
                }
                else
                {
                    day = cursor.getString(weekDayIndex);
                    schedule.add(new DayClass(day, new ArrayList<Schedule>()));
                    count++;
                    addElement(schedule, cursor, count, weekNumberIndex, idGroupIndex, auditoryIndex, startLessomIndex, finishLessomIndex, idSubjectIndex, lessonTypeIndex);
                }
            } while (cursor.moveToNext());
        }
        return schedule;
    }
    // метод заполния листа, код вынесен в отдельный метод для улучшения читаемости кода и для не повторения одинаковой группы строк
    private void addElement(List<DayClass> schedule, Cursor cursor, int count, int weekNumberIndex, int idGroupIndex, int auditoryIndex, int startLessomIndex, int finishLessomIndex, int idSubjectIndex, int lessonTypeIndex) {
        schedule.get(count).getSchedule().add(new Schedule(
                new ArrayList<>(Collections.singletonList(cursor.getInt(weekNumberIndex))),
                new ArrayList<>(Collections.singletonList(cursor.getString(idGroupIndex))),
                new ArrayList<>(Collections.singletonList(cursor.getString(auditoryIndex))),
                cursor.getString(startLessomIndex),
                cursor.getString(finishLessomIndex),
                subjectDataBaseHadler.getById(Integer.parseInt(cursor.getString(idSubjectIndex))).getName(),
                cursor.getString(lessonTypeIndex)));
    }
}
