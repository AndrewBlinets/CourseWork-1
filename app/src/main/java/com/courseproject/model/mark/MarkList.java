package com.courseproject.model.mark;

import java.util.List;

/**
 * Created by Андрей on 26.11.2017.
 */

public class MarkList {

    private List<MarkForReadJSONFile> marks;

    public MarkList(List<MarkForReadJSONFile> marks) {
        this.marks = marks;
    }

    public MarkList() {
    }

    public List<MarkForReadJSONFile> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkForReadJSONFile> marks) {
        this.marks = marks;
    }
}
