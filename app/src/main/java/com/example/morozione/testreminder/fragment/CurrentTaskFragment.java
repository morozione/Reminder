package com.example.morozione.testreminder.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.morozione.testreminder.R;
import com.example.morozione.testreminder.adapter.CurrentTaskAdapter;
import com.example.morozione.testreminder.model.ModelTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTaskFragment extends Fragment {
    private RecyclerView rvDoneTasks;

    private CurrentTaskAdapter adapter;

    public CurrentTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        rvDoneTasks = rootView.findViewById(R.id.rv_current_tasks);
        rvDoneTasks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDoneTasks.setHasFixedSize(true);

        adapter = new CurrentTaskAdapter();
        rvDoneTasks.setAdapter(adapter);

        return rootView;
    }

    public int addTask(ModelTask newModel) {
        int position = -1;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ModelTask modelTask = (ModelTask) adapter.getItem(i);
            if (newModel.getDate() < modelTask.getDate()) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            adapter.addItem(position, newModel);
        } else {
            adapter.addItem(newModel);
        }
        return position;
    }
}
