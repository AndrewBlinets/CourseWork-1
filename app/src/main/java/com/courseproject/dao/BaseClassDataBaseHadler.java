package com.courseproject.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
// базовый класс для работы с базой данных
public class BaseClassDataBaseHadler<T> extends SQLiteOpenHelper implements InterfaseDataBaseHandler<T> {

    private static final String DATABASE_NAME = "dbCourseWork"; // название БД на теле
    private static final int DataBaseVersion = 1;// версия БД
    protected Context context;

    // name table database
    protected final String NAME_TABLE_GROUP = "groupstudent";
    protected final String NAME_TABLE_MARK = "mark";
    protected final String NAME_TABLE_SCHEDULE = "schedule";
    protected final String NAME_TABLE_STUDENT = "student";
    protected final String NAME_TABLE_SUBJECT = "SUBJECT";

    //base constryction database
    protected final String KEY_ID = "_id";
    private final String TYPE_TEXT = " text ";
    private final String TYPE_INTEGER = " integer ";
    private final String COMMA = " , ";
    protected final String SELECT_ALL = "SELECT * FROM ";
    private String DELETE_TABLE = "DROP TABLE IF EXISTS ";

    // base constryction to create table
    private final String CREATE_TABEL = "create table ";
    private final String ID_PRIMARY_KEY =  " ( " + KEY_ID + " integer primary key autoincrement, " ;
    private final String END = " );";

    // table group
    protected final String KEY_NUMBER_GROUP = "numbergroup";
    protected final String KEY_ID_FACULTY = "idfaculty";

    // mark
    protected final String KEY_MARK = "mark";
    protected final String KEY_ID_STUDENT = "idstudent";
    protected final String KEY_ID_SUBJECT = "idsubject";

    // student
    protected final String KEY_NAME_STUDENT = "name";
    protected final String KEY_SURNAME_STUDENT = "surname";
    protected final String KEY_SECOND_NAME_STUDENT = "secondname";
    protected final String KEY_ID_GROUP = "idgroup";
    protected final String KEY_NUMBER_STUDENT_CARD = "studcard";
    protected final String KEY_FOTO = "foto";

    // subject
    protected final String KEY_NAME_SUBJECT = "name";

    // schedule
    protected final String KEY_NAME_DAY = "day";
    protected final String KEY_WEEK = "week";
    protected final String KEY_AUDITORY = "auditory";
    protected final String KEY_START_LESSON = "starttime";
    protected final String KEY_FINISH_LESSON = "finishtime";
    protected final String KEY_LESSON_TYPE = "lessontupe";


    public BaseClassDataBaseHadler(Context context)
    {
        super(context, DATABASE_NAME, null, DataBaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // созадются таблицы в БД
        db.execSQL(CREATE_TABEL + NAME_TABLE_GROUP + ID_PRIMARY_KEY +
                    KEY_NUMBER_GROUP + TYPE_TEXT + COMMA +
                    KEY_ID_FACULTY + TYPE_INTEGER + END);
        db.execSQL(CREATE_TABEL + NAME_TABLE_MARK + ID_PRIMARY_KEY +
                    KEY_MARK + TYPE_TEXT + COMMA +
                    KEY_ID_STUDENT + TYPE_INTEGER + COMMA +
                    KEY_ID_SUBJECT + TYPE_INTEGER + END);
        db.execSQL(CREATE_TABEL + NAME_TABLE_STUDENT + ID_PRIMARY_KEY +
                    KEY_NAME_STUDENT + TYPE_TEXT + COMMA +
                    KEY_SURNAME_STUDENT + TYPE_TEXT + COMMA +
                    KEY_SECOND_NAME_STUDENT + TYPE_TEXT + COMMA +
                    KEY_ID_GROUP + TYPE_INTEGER + COMMA +
                    KEY_NUMBER_STUDENT_CARD + TYPE_TEXT + COMMA +
                    KEY_FOTO + TYPE_TEXT + END);
        db.execSQL(CREATE_TABEL + NAME_TABLE_SUBJECT + ID_PRIMARY_KEY +
                    KEY_NAME_SUBJECT + TYPE_TEXT + END);
        db.execSQL(CREATE_TABEL + NAME_TABLE_SCHEDULE + ID_PRIMARY_KEY +
                    KEY_NAME_DAY + TYPE_TEXT + COMMA +
                    KEY_WEEK + TYPE_TEXT + COMMA +
                    KEY_ID_GROUP + TYPE_INTEGER + COMMA +
                    KEY_AUDITORY + TYPE_TEXT + COMMA +
                    KEY_START_LESSON + TYPE_TEXT + COMMA +
                    KEY_FINISH_LESSON + TYPE_TEXT + COMMA +
                    KEY_ID_SUBJECT + TYPE_INTEGER + COMMA +
                    KEY_LESSON_TYPE + TYPE_TEXT + END);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // удаляются все таблицы  из бд и создаются занова
        db.execSQL(DELETE_TABLE + NAME_TABLE_GROUP);
        db.execSQL(DELETE_TABLE + NAME_TABLE_MARK);
        db.execSQL(DELETE_TABLE + NAME_TABLE_STUDENT);
        db.execSQL(DELETE_TABLE + NAME_TABLE_SUBJECT);
        db.execSQL(DELETE_TABLE + NAME_TABLE_SCHEDULE);
        onCreate(db);
    }

    // Реализация  CRUD операуций для работы с БД,
    // в нашем случае все необходимые нам операции были переопределены в наследуемых классах

    @Override
    public void add(T t) {

    }

    @Override
    public T getById(long id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public void update(T t) {
    }

    @Override
    public void deleteById(int id) {
    }

    @Override
    public void deleteAll() {
    }
}
