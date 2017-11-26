package com.courseproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.courseproject.R;
import com.courseproject.adapter.AdapterMark;
import com.courseproject.constants.ConstantsForIntent;
import com.courseproject.dao.MarkDataBaseHadler;
import com.courseproject.model.mark.Mark;

import java.util.List;

public class FragmentMark extends Fragment {

    private static final int LAYOUT = R.layout.fragment_mark;
    private View view;
    ListView listView;

    AdapterMark adapterMark;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        Bundle bundle = this.getArguments();
        List<Mark> marks = new MarkDataBaseHadler(getContext()).getMarksByIdStudent(bundle.getLong(ConstantsForIntent.idStudent));
        adapterMark = new AdapterMark(getActivity(),marks);
        listView = (ListView)view.findViewById(R.id.listViewMark);
        listView.setAdapter(adapterMark);
        return view;
    }
}
