package com.courseproject.model;


public class Student extends BaseModel {

    private String name;
    private String surName;
    private long numberGroup;
    private String foto;

    public Student(long id, String name, String surName, long numberGroup, String foto) {
        super(id);
        this.name = name;
        this.surName = surName;
        this.numberGroup = numberGroup;
        this.foto = foto;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public long getNumberGroup() {
        return numberGroup;
    }

    public void setNumberGroup(long numberGroup) {
        this.numberGroup = numberGroup;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
