package com.courseproject.dao;

import java.util.List;
//интерфейс с базовыми CRUD операциями для работы с БД
interface InterfaseDataBaseHandler<T> {
    void add(T t); //create
    T  getById(long id);//read
    List<T> getAll();//read
    int update(T t);//update
    void deleteById(int id);//delete
    void deleteAll();//delete
}
