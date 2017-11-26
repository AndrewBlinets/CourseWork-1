package com.courseproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courseproject.R;
import com.courseproject.constants.ConstantsForIntent;
import com.courseproject.dao.StudentDataBaseHadler;
import com.courseproject.model.student.Student;

public class EducationCardFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_education_card;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        Bundle bundle = this.getArguments();
        Student student = new StudentDataBaseHadler(getContext()).getById(bundle.getLong(ConstantsForIntent.idStudent));
        ((TextView)view.findViewById(R.id.firstName)).setText(student.getName());
        ((TextView)view.findViewById(R.id.secondName)).setText(student.getSecondName());
        ((TextView)view.findViewById(R.id.surName)).setText(student.getSurName());
        ((TextView)view.findViewById(R.id.groupNumber)).setText(student.getGroup().getName());

        return view;
    }
}
