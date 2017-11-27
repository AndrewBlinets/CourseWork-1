package com.courseproject.model.student;

import com.courseproject.model.BaseModel;
import com.courseproject.model.Group;
// класс студент с параметрами для его
public class Student extends BaseModel {

    private String name;
    private String surName;
    private String secondName;
    private Group group;
    private String numberStudentCard;
    private String foto;

    public Student(long id, String name, String surName, String secondName, Group group, String numberStudentCard, String foto) {
        super(id);
        this.name = name;
        this.surName = surName;
        this.secondName = secondName;
        this.group = group;
        this.numberStudentCard = numberStudentCard;
        this.foto = foto;
    }

    public Student(String name, String surName, String secondName, Group group, String numberStudentCard, String foto) {
        this.name = name;
        this.surName = surName;
        this.secondName = secondName;
        this.group = group;
        this.numberStudentCard = numberStudentCard;
        this.foto = foto;
    }

    public Student(long id) {
        super(id);
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getNumberStudentCard() {
        return numberStudentCard;
    }

    public void setNumberStudentCard(String numberStudentCard) {
        this.numberStudentCard = numberStudentCard;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
