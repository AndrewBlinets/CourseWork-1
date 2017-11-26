package com.courseproject.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectDataBaseHadler extends BaseClassDataBaseHadler<Subject> {

    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE_SUBJECT + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE_SUBJECT;

    public SubjectDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_SUBJECT, subject.getName());
        db.insert(NAME_TABLE_SUBJECT, null, values);
        db.close();
    }

    @Override
    public Subject getById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Subject subject = new Subject();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME_SUBJECT);
            subject.setId(cursor.getInt(idIndex));
            subject.setName(cursor.getString(nameIndex));
        }
        cursor.close();
        db.close();
        return subject;
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        String selectQuery = GET_ALL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME_SUBJECT);
                subjects.add(new Subject(cursor.getLong(idIndex), cursor.getString(nameIndex)));
            } while (cursor.moveToNext());
        }

        return subjects;
    }

    @Override
    public int update(Subject subject) {
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
