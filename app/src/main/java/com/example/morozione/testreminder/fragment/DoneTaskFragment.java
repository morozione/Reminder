package com.example.morozione.testreminder.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.morozione.testreminder.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTaskFragment extends Fragment {
    private RecyclerView rvDoneTasks;

    public DoneTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        rvDoneTasks = rootView.findViewById(R.id.rv_done_tasks);
        rvDoneTasks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDoneTasks.setHasFixedSize(true);

        return rootView;
    }

}
