package com.example.morozione.testreminder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.morozione.testreminder.adapter.CurrentTaskAdapter;
import com.example.morozione.testreminder.model.ModelTask;

/**
 * Created by morozione on 11/14/17.
 */

public abstract class TaskFragment extends Fragment {
    protected RecyclerView recyclerView;

    protected CurrentTaskAdapter adapter;

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

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState);
}
