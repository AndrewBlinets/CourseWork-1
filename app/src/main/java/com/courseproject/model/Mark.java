package com.courseproject.model;

public class Mark extends BaseModel{

    private Student student;
    private Subject subject;
    private String mark;

    public Mark(long id, Student student, Subject subject, String mark) {
        super(id);
        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }

    public Mark(Student student, Subject subject, String mark) {
        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }

    public Mark() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
