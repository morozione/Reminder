package com.example.morozione.testreminder.model;

import android.graphics.Color;

import com.example.morozione.testreminder.R;

/**
 * Created by morozione on 11/14/17.
 */

public class ModelTask implements Item {
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_NORMAL = 1;
    public static final int PRIORITY_HIGH = 2;

    public static final String[] PRIORITY_LEVELS = {"Low priority", "Normal priority", "high priority"};

    public static final int STATUS_OVERDUE = 0;
    public static final int STATUS_CURRENT = 1;
    public static final int STATUS_DONE = 2;

    private String title;
    private long date;

    private int priority;
    private int status;

    public ModelTask() {
        this.status = -1;
    }

    public ModelTask(String title, long date, int priority, int status) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.status = status;
    }

    public int getPriorityColor() {
        if (getPriority() == 0) {
            if (getStatus() == STATUS_CURRENT || getStatus() == STATUS_OVERDUE) {
                return Color.parseColor(String.valueOf(R.color.priority_low));
            } else {
                return Color.parseColor(String.valueOf(R.color.priority_low_selected));
            }
        } else if (getPriority() == 1) {
            if (getStatus() == STATUS_CURRENT || getStatus() == STATUS_OVERDUE) {
                return Color.parseColor(String.valueOf(R.color.priority_normal));
            } else {
                return Color.parseColor(String.valueOf(R.color.priority_normal_selected));
            }
        } else if (getPriority() == 2) {
            if (getStatus() == STATUS_CURRENT || getStatus() == STATUS_OVERDUE) {
                return Color.parseColor(String.valueOf(R.color.priority_high));
            } else {
                return Color.parseColor(String.valueOf(R.color.priority_high_selected));
            }
        }
        return 0;
    }

    @Override
    public boolean isTask() {
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
