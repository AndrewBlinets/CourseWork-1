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
    ListView listView1;

    AdapterMark adapterMark;
    AdapterMark adapterMark1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
//        List<Mark> marks = new ArrayList<>(Arrays.asList(new Mark("Физика", 9 +""),
//                new Mark1("Опп", 6 + ""),
//                new Mark1("Философия", 2 +""),
//                new Mark1("ФКУ", "допуск"),
//                new Mark1("ЗКП",  "не допуск")
//                ));
//        adapterMark = new AdapterMark(getActivity(),marks);
//        listView = (ListView)view.findViewById(R.id.listViewMark);
//        listView.setAdapter(adapterMark);
//        List<Mark1> marks1 = new ArrayList<>(Arrays.asList(new Mark1("ФСК", "зачёт"),
//                new Mark1("ФСБ", "не зачет"),
//                new Mark1("ФЭСБ", "не допуск"),
//                new Mark1("ДКУ", "зачёт")
//        ));
//        adapterMark1 = new AdapterMark(getActivity(),marks1);
        listView1 = (ListView)view.findViewById(R.id.listViewRecord);
        listView1.setAdapter(adapterMark1);
        return view;
    }
}
