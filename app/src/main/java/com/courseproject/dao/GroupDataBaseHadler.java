package com.courseproject.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Group;

import java.util.ArrayList;
import java.util.List;
// класс для работы с таблице group в бд
public class GroupDataBaseHadler extends BaseClassDataBaseHadler<Group> {

    // неободимые SQL запросы
    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE_GROUP + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE_GROUP;
    private String GET_BY_NUMBER = SELECT_ALL + NAME_TABLE_GROUP + " WHERE " + KEY_NUMBER_GROUP + " = ?";

    public GroupDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Group group) { // перопределенный метод
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NUMBER_GROUP, group.getName());
        values.put(KEY_ID_FACULTY, group.getIdFacultu());
        db.insert(NAME_TABLE_GROUP, null, values);
        db.close();
    }

    @Override
    public Group getById(long id) {// перопределенный метод
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
    public List<Group> getAll() {// перопределенный метод
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

    public Group getGroupByNamber(String number){// метод для получения группы по номеру
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_NUMBER, new String[]{number});
        Group group = null;
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NUMBER_GROUP);
            int idFacultyIndex = cursor.getColumnIndex(KEY_ID_FACULTY);
            group = new Group(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getLong(idFacultyIndex));
        }
        cursor.close();
        db.close();
        return group;
    }
}
