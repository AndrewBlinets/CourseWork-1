package com.courseproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.courseproject.constant.DataBaseConstant;
import com.courseproject.model.Faculty;
import com.courseproject.model.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentDataBaseHadler extends SQLiteOpenHelper implements InterfaseDataBaseHandler<Student> {

    String NAME_TABLE  = "student";

    private final String KEY_ID = "_id";
    private final String KEY_NAME = "name";
    private final String KEY_SURNAME = "surname";
    private final String KEY_SECOND_NAME = "secondname";
    private final String KEY_ID_GROUP = "idgroup";
    private final String KEY_NUMBER_STUDENT_CARD = "studcard";
    private final String KEY_FOTO = "foto";



    private final String SELECT_ALL = "SELECT * FROM ";
    private String DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    private String  GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
    private String GET_ALL = SELECT_ALL + NAME_TABLE;

    public StudentDataBaseHadler(Context context) {
        super(context, DataBaseConstant.DATABASE_NAME, null, DataBaseConstant.DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_SURNAME + " text, " +
                KEY_SECOND_NAME + " text, " +
                KEY_ID_GROUP + " integer, " +
                KEY_NUMBER_STUDENT_CARD + " text, " +
                KEY_FOTO + " text " + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void add(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_SURNAME, student.getSurName());
        values.put(KEY_SECOND_NAME, student.getSecondName());
        values.put(KEY_ID_GROUP, student.getIdGroup());
        values.put(KEY_NUMBER_STUDENT_CARD, student.getNumberStudentCard());
        values.put(KEY_FOTO, student.getFoto());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    @Override
    public Student getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
        Student student = new Student();
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int surNameIndex = cursor.getColumnIndex(KEY_SURNAME);
            int secondNameIndex = cursor.getColumnIndex(KEY_SECOND_NAME);
            int groupIdIndex = cursor.getColumnIndex(KEY_ID_GROUP);
            int numberStudentCardIndex = cursor.getColumnIndex(KEY_NUMBER_STUDENT_CARD);
            int fotoIndex = cursor.getColumnIndex(KEY_FOTO);
            student.setId(cursor.getInt(idIndex));
            student.setName(cursor.getString(nameIndex));
            student.setFoto(cursor.getString(fotoIndex));
            student.setIdGroup(cursor.getLong(groupIdIndex));
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
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int surNameIndex = cursor.getColumnIndex(KEY_SURNAME);
                int secondNameIndex = cursor.getColumnIndex(KEY_SECOND_NAME);
                int groupIdIndex = cursor.getColumnIndex(KEY_ID_GROUP);
                int numberStudentCardIndex = cursor.getColumnIndex(KEY_NUMBER_STUDENT_CARD);
                int fotoIndex = cursor.getColumnIndex(KEY_FOTO);
                students.add(new Student(cursor.getInt(idIndex), cursor.getString(nameIndex),
                        cursor.getString(surNameIndex), cursor.getString(secondNameIndex),
                        cursor.getLong(groupIdIndex),cursor.getString(numberStudentCardIndex),cursor.getString(fotoIndex)));
            } while (cursor.moveToNext());
        }

        return students;
    }

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
