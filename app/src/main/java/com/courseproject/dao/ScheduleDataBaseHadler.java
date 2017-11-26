package com.courseproject.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Schedule;

import java.util.List;

/**
 * Created by Андрей on 25.11.2017.
 */

public class ScheduleDataBaseHadler extends BaseClassDataBaseHadler<Schedule>{

    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE_SCHEDULE + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE_SCHEDULE;

    public ScheduleDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Schedule schedule) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, schedule.getName());
//        db.insert(NAME_TABLE, null, values);
//        db.close();
    }

    @Override
    public Schedule getById(long id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
//        Faculty faculty = new Faculty();
//        if(cursor.moveToFirst())
//        {
//            int idIndex = cursor.getColumnIndex(KEY_ID);
//            int nameIndex = cursor.getColumnIndex(KEY_NAME);
//            faculty.setId(cursor.getInt(idIndex));
//            faculty.setName(cursor.getString(nameIndex));
//        }
//        cursor.close();
//        db.close();
//        return faculty;
        return null;
    }

    @Override
    public List<Schedule> getAll() {
//        List<Faculty> faculties = new ArrayList<>();
//        String selectQuery = GET_ALL;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int idIndex = cursor.getColumnIndex(KEY_ID);
//                int nameIndex = cursor.getColumnIndex(KEY_NAME);
//                faculties.add(new Faculty(cursor.getInt(idIndex), cursor.getString(nameIndex)));
//            } while (cursor.moveToNext());
//        }
//
//        return faculties;
        return null;
    }

    @Override
    public int update(Schedule schedule) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
