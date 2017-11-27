package com.courseproject.model.mark;

import com.courseproject.model.BaseModel;
import com.courseproject.model.Subject;
import com.courseproject.model.student.Student;
// моель для работы с оценками
public class Mark extends BaseModel {

    private Student student;//студент
    private Subject subject;// предмет
    private String mark;//оценка

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
