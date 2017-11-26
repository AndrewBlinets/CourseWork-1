package com.courseproject.dao;

import java.util.List;

interface InterfaseDataBaseHandler<T> {
    void add(T t);
    T  getById(long id);
    List<T> getAll();
    int update(T t);
    void deleteById(int id);
    void deleteAll();
}
