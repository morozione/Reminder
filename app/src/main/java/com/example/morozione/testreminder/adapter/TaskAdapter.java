package com.example.morozione.testreminder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.morozione.testreminder.R;
import com.example.morozione.testreminder.fragment.TaskFragment;
import com.example.morozione.testreminder.model.Item;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public abstract class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    List<Item> itemList;

    TaskFragment taskFragment;

    public TaskAdapter(TaskFragment taskFragment) {
        this.taskFragment = taskFragment;
        itemList = new ArrayList<>();
    }

    public Item getItem(int position) {
        return itemList.get(position);
    }

    public void addItem(Item item) {
        itemList.add(item);
        notifyItemChanged(getItemCount() - 1);
    }

    public void addItem(int location, Item item) {
        itemList.add(location, item);
        notifyItemChanged(location);
    }

    public void removeItem(int location) {
        if (location >= 0 && location < itemList.size()) {
            itemList.remove(location);
            notifyItemRemoved(location);
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvTitle;
        protected TextView tvDate;
        protected CircleImageView civPriority;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDate = itemView.findViewById(R.id.tvTaskDate);
            civPriority = itemView.findViewById(R.id.civ_task_priority);
        }
    }

    public TaskFragment getTaskFragment() {
        return taskFragment;
    }
}
