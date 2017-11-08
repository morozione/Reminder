package com.example.morozione.testreminder.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.morozione.testreminder.R;

/**
 * Created by morozione on 11/03/17.
 */

public class AddingDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.done_task);

        View container = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_task, null);

        TextInputLayout tilTitle = container.findViewById(R.id.tilDialogTaskTitle);
        EditText etTitle = tilTitle.getEditText();

        TextInputLayout tilDate = container.findViewById(R.id.tilDialogTaskDate);
        EditText etDate = tilDate.getEditText();

        TextInputLayout tilTime = container.findViewById(R.id.tilDialogTaskTime);
        EditText etTime = tilTime.getEditText();

        etTitle.setHint(getResources().getString(R.string.task_title));
        etTime.setHint(getResources().getString(R.string.task_Time));
        etDate.setHint(getResources().getString(R.string.task_Date));

        builder.setView(container);

        return super.onCreateDialog(savedInstanceState);
    }
}
