package com.courseproject.model;

public class Group extends BaseModel {

    private String number;
    private String facultu;

    public Group(long id, String number, String facultu) {
        super(id);
        this.number = number;
        this.facultu = facultu;
    }

    public Group() {
        super();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFacultu() {
        return facultu;
    }

    public void setFacultu(String facultu) {
        this.facultu = facultu;
    }
}
