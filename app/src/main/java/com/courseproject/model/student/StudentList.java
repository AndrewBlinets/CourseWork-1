package com.courseproject.model.student;

import java.util.List;

/**
 * Created by Андрей on 26.11.2017.
 */

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
