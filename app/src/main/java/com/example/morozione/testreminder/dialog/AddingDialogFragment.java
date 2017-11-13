package com.example.morozione.testreminder.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.morozione.testreminder.R;

public class AddingDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.done_task);

        @SuppressLint("InflateParams")
        View container = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_task, null);

        TextInputLayout tilTitle = container.findViewById(R.id.tilDialogTaskTitle);
        final EditText etTitle = tilTitle.getEditText();

        TextInputLayout tilDate = container.findViewById(R.id.tilDialogTaskDate);
        final EditText etDate = tilDate.getEditText();

        TextInputLayout tilTime = container.findViewById(R.id.tilDialogTaskTime);
        final EditText etTime = tilTime.getEditText();

        assert etTitle != null;
        etTitle.setHint(getResources().getString(R.string.task_title));
        assert etTime != null;
        etTime.setHint(getResources().getString(R.string.task_Time));
        assert etDate != null;
        etDate.setHint(getResources().getString(R.string.task_Date));

        builder.setView(container);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTime.length() == 0) {
                    etTime.setText(" ");
                }
//                DialogFragment datePickerFragment = new DatePickerFragment() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int mounth, int dayOfMonth) {
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.set(year, mounth, dayOfMonth);
//                        etDate.setText(Utils.getDAte(calendar.getTimeInMillis()));
//                    }
//
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        super.onCancel(dialog);
//                        etTime.setText(null);
//                    }
//                };
//                datePickerFragment.show(getFragmentManager(), "DatePickerFragment");
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTime.length() == 0) {
                    etDate.setText(" ");
                }
//                DialogFragment timePickerFragment = new TimePickerFragment() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                        Calendar c = Calendar.getInstance();
//                        c.set(0, 0, 0, i, i1);
//                        etTime.setText(Utils.getTiem(c.getTimeInMillis()));
//                    }
//
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        super.onCancel(dialog);
//                        etDate.setText(null);
//                    }
//                };
//                timePickerFragment.show(getFragmentManager(), "TimePickerFragment");
            }
        });

        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.Cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        return super.onCreateDialog(savedInstanceState);
    }
}
