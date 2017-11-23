package com.courseproject.model;

/**
 * Created by Андрей on 22.11.2017.
 */

public class Faculty extends BaseModel {
    private String name;

    public Faculty(long id, String name) {
        super(id);
        this.name = name;
    }

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
