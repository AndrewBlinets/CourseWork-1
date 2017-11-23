package com.courseproject.model;

public class Subject extends BaseModel {

    private String name;

    public Subject(long id, String name) {
        super(id);
        this.name = name;
    }

    public Subject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }
}