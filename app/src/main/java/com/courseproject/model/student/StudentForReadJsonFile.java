package com.courseproject.model.student;

import com.courseproject.model.BaseModel;
// аналогично как и с оценками
public class StudentForReadJsonFile extends BaseModel {
    private String name;
    private String surName;
    private String secondName;
    private long idGroup;
    private String numberStudentCard;
    private String foto;

    public StudentForReadJsonFile(long id,String name, String surName, String secondName, long idGroup, String numberStudentCard, String foto) {
        super(id);
        this.name = name;
        this.surName = surName;
        this.secondName = secondName;
        this.idGroup = idGroup;
        this.numberStudentCard = numberStudentCard;
        this.foto = foto;
    }

    public StudentForReadJsonFile() {
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

    public long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
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
