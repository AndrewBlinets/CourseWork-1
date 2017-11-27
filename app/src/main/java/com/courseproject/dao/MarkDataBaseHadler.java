package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.mark.Mark;

import java.util.ArrayList;
import java.util.List;

//  класс для работы с таблицей mark БД
public class MarkDataBaseHadler extends BaseClassDataBaseHadler<Mark> {
    //необходимые SQL запросы
    private final String GET_BY_ID_STUDENT = SELECT_ALL + NAME_TABLE_MARK + " WHERE " + KEY_ID_STUDENT + " = ?";;
    private String GET_BY_ID = SELECT_ALL + NAME_TABLE_MARK + " WHERE " + KEY_ID + " = ?";

    public MarkDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Mark mark) {// перопределенный метод
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,mark.getId());
        values.put(KEY_MARK, mark.getMark());
        values.put(KEY_ID_STUDENT, mark.getStudent().getId());
        values.put(KEY_ID_SUBJECT, mark.getSubject().getId());
        db.insert(NAME_TABLE_MARK, null, values);
        db.close();
    }

    @Override
    public Mark getById(long id) {// перопределенный метод
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Mark mark = null;
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int markIndex = cursor.getColumnIndex(KEY_MARK);
            int idStudentIndex = cursor.getColumnIndex(KEY_ID_STUDENT);
            int idSubjectIndex = cursor.getColumnIndex(KEY_ID_SUBJECT);
            mark = new Mark();
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
    public int update(Mark mark) {// перопределенный метод
        SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("UPDATE " + NAME_TABLE_MARK + " SET " + KEY_MARK + " = '" + mark.getMark() + "', " +
                    KEY_ID_STUDENT + " = '" + mark.getStudent().getId() + "', " +
                    KEY_ID_SUBJECT + " = '" + mark.getSubject().getId() + "' WHERE " + KEY_ID + " = " + mark.getId());
            return 1;
    }

    public List<Mark> getMarksByIdStudent(long id) {// получения оценок по id студента
        List<Mark> marks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID_STUDENT,  new String[]{String.valueOf(id)});
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
}
