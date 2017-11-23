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
import com.courseproject.model.Mark;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Mark> marks = new ArrayList<>(Arrays.asList(new Mark(1,1,1, 10 +""),
                new Mark(1,1,1, 9 +""),
                new Mark(1,1,1, 8 +""),
                new Mark(1,1,1, 7 +"")));
        adapterMark = new AdapterMark(getActivity(),marks);
        listView = (ListView)view.findViewById(R.id.listViewMark);
        listView.setAdapter(adapterMark);
        return view;
    }
}
