package com.courseproject.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class BaseClassDataBaseHadler<T> extends SQLiteOpenHelper implements InterfaseDataBaseHandler<T> {

    protected String NAME_TABLE = "";

    protected final String KEY_ID = "_id";

    protected String CREATE_TABLE = "";

    private final String SELECT_ALL = "SELECT * FROM ";
    protected String GET_BY_ID ="";
    protected String GET_ALL = "";
    private String DELETE_TABLE = "";
    private static final String DATABASE_NAME = "dbCourseWork";
    private static final int DataBaseVersion = 1;

    private static int dataBaseVerson;

    public BaseClassDataBaseHadler(Context context, String nameTable)
    {
        super(context, DATABASE_NAME, null, DataBaseVersion);
        NAME_TABLE = nameTable;
        dataBaseVerson = DataBaseVersion;
        GET_BY_ID = SELECT_ALL + NAME_TABLE + " WHERE " + KEY_ID + " = ?";
        GET_ALL = SELECT_ALL + NAME_TABLE;
        DELETE_TABLE = "DROP TABLE IF EXISTS " + NAME_TABLE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void add(T t) {

    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public int update(T t) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
