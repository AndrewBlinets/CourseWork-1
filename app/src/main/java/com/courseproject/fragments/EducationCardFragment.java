package com.courseproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courseproject.R;

public class EducationCardFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_education_card;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        ((TextView)view.findViewById(R.id.firstName)).setText("Иван");
        ((TextView)view.findViewById(R.id.secondName)).setText("Иванович");
        ((TextView)view.findViewById(R.id.surName)).setText("Иванов");
        return view;
    }
}
