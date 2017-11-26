package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.courseproject.model.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentDataBaseHadler extends BaseClassDataBaseHadler<Student> {

    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE_STUDENT + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE_STUDENT;

    public StudentDataBaseHadler(Context context) {
        super(context);
    }

    @Override
    public void add(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_STUDENT, student.getName());
        values.put(KEY_SURNAME_STUDENT, student.getSurName());
        values.put(KEY_SECOND_NAME_STUDENT, student.getSecondName());
        values.put(KEY_ID_GROUP, student.getGroup().getId());
        values.put(KEY_NUMBER_STUDENT_CARD, student.getNumberStudentCard());
        values.put(KEY_FOTO, student.getFoto());
        db.insert(NAME_TABLE_STUDENT, null, values);
        db.close();
    }

    @Override
    public Student getById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Student student = new Student();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME_STUDENT);
            int surNameIndex = cursor.getColumnIndex(KEY_SURNAME_STUDENT);
            int secondNameIndex = cursor.getColumnIndex(KEY_SECOND_NAME_STUDENT);
            int groupIdIndex = cursor.getColumnIndex(KEY_ID_GROUP);
            int numberStudentCardIndex = cursor.getColumnIndex(KEY_NUMBER_STUDENT_CARD);
            int fotoIndex = cursor.getColumnIndex(KEY_FOTO);
            student.setId(cursor.getInt(idIndex));
            student.setName(cursor.getString(nameIndex));
            student.setFoto(cursor.getString(fotoIndex));
            student.setGroup(new GroupDataBaseHadler(context).getById(cursor.getLong(groupIdIndex)));
            student.setSurName(cursor.getString(surNameIndex));
            student.setSecondName(cursor.getString(secondNameIndex));
            student.setNumberStudentCard(cursor.getString(numberStudentCardIndex));
        }
        cursor.close();
        db.close();
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String selectQuery = GET_ALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID_STUDENT);
                int nameIndex = cursor.getColumnIndex(KEY_NAME_STUDENT);
                int surNameIndex = cursor.getColumnIndex(KEY_SURNAME_STUDENT);
                int secondNameIndex = cursor.getColumnIndex(KEY_SECOND_NAME_STUDENT);
                int groupIdIndex = cursor.getColumnIndex(KEY_ID_GROUP);
                int numberStudentCardIndex = cursor.getColumnIndex(KEY_NUMBER_STUDENT_CARD);
                int fotoIndex = cursor.getColumnIndex(KEY_FOTO);
                students.add(new Student(cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(surNameIndex),
                        cursor.getString(secondNameIndex),
                        new GroupDataBaseHadler(context).getById(cursor.getLong(groupIdIndex)),
                        cursor.getString(numberStudentCardIndex),
                        cursor.getString(fotoIndex)));
            } while (cursor.moveToNext());
        }

        return students;
    }

    @Override
    public int update(Student student) {
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
