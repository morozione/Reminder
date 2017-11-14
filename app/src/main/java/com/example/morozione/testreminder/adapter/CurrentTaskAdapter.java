package com.example.morozione.testreminder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.morozione.testreminder.R;
import com.example.morozione.testreminder.Utils;
import com.example.morozione.testreminder.model.Item;
import com.example.morozione.testreminder.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morozione on 11/14/17.
 */

public class CurrentTaskAdapter extends RecyclerView.Adapter<CurrentTaskAdapter.ViewHolder> {
    public static final int TYPE_TASK = 0;
    public static final int TYPE_SEPARATOR = 1;

    private List<Item> itemList = new ArrayList<>();

    public CurrentTaskAdapter() {
    }

    public CurrentTaskAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TASK) {
            View rooView = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_task, parent, false);
            return new ViewHolder(rooView);
        } else if (viewType == TYPE_SEPARATOR) {
            return null;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = getItem(position);

        if (item.isTask()) {
            holder.itemView.setEnabled(true);

            ModelTask model = (ModelTask) item;

            holder.tvTitle.setText(model.getTitle());
            if (model.getDate() != 0) {
                holder.tvDate.setText(Utils.getFullDate(model.getDate()));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isTask()) {
            return TYPE_TASK;
        }
        return TYPE_SEPARATOR;
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

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDate = itemView.findViewById(R.id.tvTaskDate);
        }
    }
}
