package com.courseproject.model.schedules;

import java.util.List;

/**
 * Created by Андрей on 26.11.2017.
 */

public class Schedules {
    private List<DayClass> schedules;

    public Schedules(List<DayClass> schedules) {
        this.schedules = schedules;
    }

    public Schedules() {
    }

    public List<DayClass> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<DayClass> schedules) {
        this.schedules = schedules;
    }
}
