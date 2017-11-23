package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.courseproject.constant.DataBaseConstant;
import com.courseproject.model.Faculty;

import java.util.ArrayList;
import java.util.List;


public class FacultyDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Faculty> {

    String NAME_TABLE  = "faculty";

    private final String KEY_NAME = "name";
    private final String KEY_ID = "_id";


    private final String SELECT_ALL = "SELECT * FROM ";
    private String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE;

    public FacultyDataBaseHadler(Context context) {
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
    public void add(Faculty faculty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, faculty.getName());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Faculty getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Faculty faculty = new Faculty();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            faculty.setId(cursor.getInt(idIndex));
            faculty.setName(cursor.getString(nameIndex));
        }
        cursor.close();
        db.close();
        return faculty;
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faculties = new ArrayList<>();
        String selectQuery = GET_ALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                faculties.add(new Faculty(cursor.getInt(idIndex), cursor.getString(nameIndex)));
            } while (cursor.moveToNext());
        }

        return faculties;
    }

    @Override
    public int update(Faculty faculty) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
