package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.courseproject.constant.DataBaseConstant;
import com.courseproject.model.Mark;

import java.util.ArrayList;
import java.util.List;


public class MarkDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Mark> {

    String NAME_TABLE  = "mark";

    private final String KEY_ID = "_id";
    private final String KEY_MARK = "mark";
    private final String KEY_ID_STUDENT = "idstudent";
    private final String KEY_ID_SUBJECT = "idsubject";


    private final String SELECT_ALL = "SELECT * FROM ";
    private String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE;

    public MarkDataBaseHadler(Context context) {
        super(context, DataBaseConstant.DATABASE_NAME, null, DataBaseConstant.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_MARK + " text, " +
                KEY_ID_STUDENT + " integer, " +
                KEY_ID_SUBJECT + " integer " + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void add(Mark mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MARK, mark.getMark());
        values.put(KEY_ID_STUDENT, mark.getIdStudent());
        values.put(KEY_ID_SUBJECT, mark.getIdSubject());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Mark getById(int id) {
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
            mark.setIdStudent(cursor.getLong(idStudentIndex));
            mark.setIdSubject(cursor.getLong(idSubjectIndex));
        }
        cursor.close();
        db.close();
        return mark;
    }

    @Override
    public List<Mark> getAll() {
        List<Mark> faculties = new ArrayList<>();
        String selectQuery = GET_ALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int markIndex = cursor.getColumnIndex(KEY_MARK);
                int idStudentIndex = cursor.getColumnIndex(KEY_ID_STUDENT);
                int idSubjectIndex = cursor.getColumnIndex(KEY_ID_SUBJECT);
                faculties.add(new Mark(cursor.getInt(idIndex), cursor.getLong(idStudentIndex), cursor.getLong(idSubjectIndex),cursor.getString(markIndex)));
            } while (cursor.moveToNext());
        }

        return faculties;
    }

    @Override
    public int update(Mark mark) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
