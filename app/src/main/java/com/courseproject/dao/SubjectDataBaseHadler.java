package com.courseproject.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Subject;
//класс для работы с предметами в БД
public class SubjectDataBaseHadler extends BaseClassDataBaseHadler<Subject> {

    private final String  GET_BY_ID = SELECT_ALL + NAME_TABLE_SUBJECT + " WHERE " + KEY_ID + " = ?";
    private final String GET_ALL = SELECT_ALL + NAME_TABLE_SUBJECT;
    private final String GET_BY_NAME = SELECT_ALL + NAME_TABLE_SUBJECT + " WHERE " + KEY_NAME_SUBJECT + " = ?";;

    public SubjectDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Subject subject) {// перопределенный метод
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_SUBJECT, subject.getName());
        db.insert(NAME_TABLE_SUBJECT, null, values);
        db.close();
    }

    @Override
    public Subject getById(long id) {// перопределенный метод
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

    public Subject getByName(String subjectName) { // получения объекта предмет по его названию
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_NAME, new String[]{subjectName});
        Subject subject = null;
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME_SUBJECT);
            subject = new Subject(cursor.getInt(idIndex), cursor.getString(nameIndex));
        }
        cursor.close();
        db.close();
        return subject;
    }
}
