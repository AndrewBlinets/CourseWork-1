package com.courseproject.model.schedules;

import java.util.List;

// класс для работы с расписанием, каждый день может быть несколько разных пар
public class DayClass {
    private String weekDay;
    private List<Schedule> schedule;

    public DayClass(String weekDay, List<Schedule> schedule) {
        this.weekDay = weekDay;
        this.schedule = schedule;
    }

    public DayClass() {
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
