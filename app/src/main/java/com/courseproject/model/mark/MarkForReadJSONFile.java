package com.courseproject.model.mark;

import com.courseproject.model.BaseModel;
// так как мы работаем с JSON файлами, и что б облегчить процесс их редактирования
// и уменьшить объем информации в файле и ее дублирования, для парсинга с помощью библиотеки
// необходим класс с соотвествующимии палями
public class MarkForReadJSONFile extends BaseModel {

    private long idStudent; // id студента
    private long idSubject; // id предмета
    private String mark; // оценка

    public MarkForReadJSONFile(long id, long idStudent, long idSubject, String mark) {
        super(id);
        this.idStudent = idStudent;
        this.idSubject = idSubject;
        this.mark = mark;
    }

    public MarkForReadJSONFile() {
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
