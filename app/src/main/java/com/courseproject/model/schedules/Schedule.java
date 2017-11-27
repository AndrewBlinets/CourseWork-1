package com.courseproject.model.schedules;

import java.util.List;
// класс с полями для одной пары
public class Schedule{
    private List<Integer> weekNumber;
    private List<String> studentGroup;
    private List<String> auditory;
    private String startLessonTime;
    private String endLessonTime;
    private String subject;
    private String lessonType;

    public Schedule() {
    }

    public Schedule(List<Integer> weekNumber, List<String> studentGroup, List<String> auditory, String startLessonTime, String endLessonTime, String subject, String lessonType) {
        this.weekNumber = weekNumber;
        this.studentGroup = studentGroup;
        this.auditory = auditory;
        this.startLessonTime = startLessonTime;
        this.endLessonTime = endLessonTime;
        this.subject = subject;
        this.lessonType = lessonType;
    }

    public List<Integer> getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(List<Integer> weekNumber) {
        this.weekNumber = weekNumber;
    }

    public List<String> getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(List<String> studentGroup) {
        this.studentGroup = studentGroup;
    }

    public List<String> getAuditory() {
        return auditory;
    }

    public void setAuditory(List<String> auditory) {
        this.auditory = auditory;
    }

    public String getStartLessonTime() {
        return startLessonTime;
    }

    public void setStartLessonTime(String startLessonTime) {
        this.startLessonTime = startLessonTime;
    }

    public String getEndLessonTime() {
        return endLessonTime;
    }

    public void setEndLessonTime(String endLessonTime) {
        this.endLessonTime = endLessonTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }
}
