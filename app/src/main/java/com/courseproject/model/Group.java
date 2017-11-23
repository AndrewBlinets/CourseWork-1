package com.courseproject.model;

public class Group extends BaseModel {

    private String name;
    private long idFacultu;

    public Group(long id, String name, long idFacultu) {
        super(id);
        this.name = name;
        this.idFacultu = idFacultu;
    }

    public Group() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdFacultu() {
        return idFacultu;
    }

    public void setIdFacultu(long idFacultu) {
        this.idFacultu = idFacultu;
    }
}
