package com.courseproject.model;

public class Mark extends BaseModel{

    private long idStudent;
    private long idSubject;
    private String mark;

    public Mark(long id, long idStudent, long idSubject, String mark) {
        super(id);
        this.idStudent = idStudent;
        this.idSubject = idSubject;
        this.mark = mark;
    }

    public Mark() {
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(long idSubject) {
        this.idSubject = idSubject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
