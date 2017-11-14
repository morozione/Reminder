package com.example.morozione.testreminder.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.morozione.testreminder.R;
import com.example.morozione.testreminder.Utils;
import com.example.morozione.testreminder.fragment.CurrentTaskFragment;
import com.example.morozione.testreminder.model.Item;
import com.example.morozione.testreminder.model.ModelTask;

/**
 * Created by morozione on 11/14/17.
 */

public class CurrentTaskAdapter extends TaskAdapter {
    public static final int TYPE_TASK = 0;
    public static final int TYPE_SEPARATOR = 1;

    public CurrentTaskAdapter(CurrentTaskFragment taskFragment) {
        super(taskFragment);
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

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Item item = getItem(position);

        if (item.isTask()) {
            holder.itemView.setEnabled(true);
            final ModelTask model = (ModelTask) item;

            final View itemView = holder.itemView;
            final Resources resources = itemView.getResources();

            holder.tvTitle.setText(model.getTitle());
            if (model.getDate() != 0) {
                holder.tvDate.setText(Utils.getFullDate(model.getDate()));
            } else {
                holder.tvDate.setText("");
            }
            itemView.setVisibility(View.VISIBLE);

            itemView.setBackgroundColor(resources.getColor(R.color.gray_50));

            holder.tvTitle.setBackgroundColor(resources.getColor(R.color.primaryText));
            holder.tvDate.setBackgroundColor(resources.getColor(R.color.secondaryText));
            holder.civPriority.setBackgroundColor(model.getPriorityColor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    model.setStatus(ModelTask.STATUS_DONE);

                    itemView.setBackgroundColor(resources.getColor(R.color.gray_200));

                    holder.tvTitle.setBackgroundColor(resources.getColor(R.color.secondaryText));
                    holder.tvDate.setBackgroundColor(resources.getColor(R.color.dividerColor));
                    holder.civPriority.setBackgroundColor(model.getPriorityColor());

                    ObjectAnimator flipIn = ObjectAnimator.ofFloat(model.getPriority(),
                            "rotation", -180f, 0f);

                    flipIn.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isTask()) {
            return TYPE_TASK;
        }
        return TYPE_SEPARATOR;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
