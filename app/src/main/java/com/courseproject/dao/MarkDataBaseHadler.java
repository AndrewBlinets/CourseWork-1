package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Mark;

import java.util.ArrayList;
import java.util.List;


public class MarkDataBaseHadler extends BaseClassDataBaseHadler<Mark> {

    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE_MARK + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE_MARK;

    public MarkDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Mark mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MARK, mark.getMark());
        values.put(KEY_ID_STUDENT, mark.getStudent().getId());
        values.put(KEY_ID_SUBJECT, mark.getSubject().getId());
        db.insert(NAME_TABLE_MARK, null, values);
        db.close();
    }

    @Override
    public Mark getById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Mark mark = new Mark();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int markIndex = cursor.getColumnIndex(KEY_MARK);
            int idStudentIndex = cursor.getColumnIndex(KEY_ID_STUDENT);
            int idSubjectIndex = cursor.getColumnIndex(KEY_ID_SUBJECT);
            mark.setId(cursor.getInt(idIndex));
            mark.setMark(cursor.getString(markIndex));
            mark.setStudent(new StudentDataBaseHadler(context).getById(cursor.getLong(idStudentIndex)));
            mark.setSubject(new SubjectDataBaseHadler(context).getById(cursor.getLong(idSubjectIndex)));
        }
        cursor.close();
        db.close();
        return mark;
    }

    @Override
    public List<Mark> getAll() {
        List<Mark> marks = new ArrayList<>();
        String selectQuery = GET_ALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int markIndex = cursor.getColumnIndex(KEY_MARK);
                int idStudentIndex = cursor.getColumnIndex(KEY_ID_STUDENT);
                int idSubjectIndex = cursor.getColumnIndex(KEY_ID_SUBJECT);
                marks.add(new Mark(cursor.getInt(idIndex),
                        new StudentDataBaseHadler(context).getById(cursor.getLong(idStudentIndex)),
                        new SubjectDataBaseHadler(context).getById(cursor.getLong(idSubjectIndex)),
                        cursor.getString(markIndex)));
            } while (cursor.moveToNext());
        }

        return marks;
    }

    @Override
    public int update(Mark mark) {
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
