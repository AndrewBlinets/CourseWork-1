package com.courseproject.model.student;

import java.util.List;
// класс с поелм лист студентов для парсинга
public class StudentList {
    private List<StudentForReadJsonFile> students;

    public StudentList(List<StudentForReadJsonFile> students) {
        this.students = students;
    }

    public StudentList() {
    }

    public List<StudentForReadJsonFile> getStudents() {
        return students;
    }

    public void setStudents(List<StudentForReadJsonFile> students) {
        this.students = students;
    }
}
