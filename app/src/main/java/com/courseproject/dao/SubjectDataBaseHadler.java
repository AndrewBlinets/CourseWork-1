package com.courseproject.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.courseproject.constant.DataBaseConstant;
import com.courseproject.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Subject> {

    String NAME_TABLE  = "subject";

    private final String KEY_NAME = "name";
    private final String KEY_ID = "_id";


    private final String SELECT_ALL = "SELECT * FROM ";
    private String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE;

    public SubjectDataBaseHadler(Context context) {
        super(context, DataBaseConstant.DATABASE_NAME, null, DataBaseConstant.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text " + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void add(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subject.getName());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Subject getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Subject subject = new Subject();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
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

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                subjects.add(new Subject(cursor.getInt(idIndex), cursor.getString(nameIndex)));
            } while (cursor.moveToNext());
        }

        return subjects;
    }

    @Override
    public int update(Subject subject) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
