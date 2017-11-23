package com.courseproject.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.courseproject.constant.DataBaseConstant;
import com.courseproject.model.Faculty;
import com.courseproject.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Group> {

    String NAME_TABLE  = "group";

    private final String KEY_NAME = "name";
    private final String KEY_ID = "_id";
    private final String KEY_ID_FACULTY = "idfaculty";


    private final String SELECT_ALL = "SELECT * FROM ";
    private String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE;

    public GroupDataBaseHadler(Context context) {
        super(context, DataBaseConstant.DATABASE_NAME, null, DataBaseConstant.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_ID_FACULTY + " integer " + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void add(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, group.getName());
        values.put(KEY_ID_FACULTY, group.getIdFacultu());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Group getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Group group = new Group();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int idFacultyIndex = cursor.getColumnIndex(KEY_ID_FACULTY);
            group.setId(cursor.getInt(idIndex));
            group.setName(cursor.getString(nameIndex));
            group.setIdFacultu(cursor.getLong(idFacultyIndex));
        }
        cursor.close();
        db.close();
        return group;
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        String selectQuery = GET_ALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int idFacultyIndex = cursor.getColumnIndex(KEY_ID_FACULTY);
                groups.add(new Group(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getLong(idFacultyIndex)));
            } while (cursor.moveToNext());
        }

        return groups;
    }

    @Override
    public int update(Group group) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
