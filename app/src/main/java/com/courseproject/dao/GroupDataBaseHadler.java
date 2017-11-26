package com.courseproject.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupDataBaseHadler extends BaseClassDataBaseHadler<Group> {

    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE_GROUP + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE_GROUP;

    public GroupDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NUMBER_GROUP, group.getName());
        values.put(KEY_ID_FACULTY, group.getIdFacultu());
        db.insert(NAME_TABLE_GROUP, null, values);
        db.close();
    }

    @Override
    public Group getById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Group group = new Group();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NUMBER_GROUP);
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
                int nameIndex = cursor.getColumnIndex(KEY_NUMBER_GROUP);
                int idFacultyIndex = cursor.getColumnIndex(KEY_ID_FACULTY);
                groups.add(new Group(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getLong(idFacultyIndex)));
            } while (cursor.moveToNext());
        }

        return groups;
    }

    @Override
    public int update(Group group) {
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
