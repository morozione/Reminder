package com.example.morozione.testreminder.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.morozione.testreminder.R;
import com.example.morozione.testreminder.Utils;
import com.example.morozione.testreminder.model.ModelTask;

import java.util.Calendar;

public class AddingDialogFragment extends DialogFragment {

    private AddingTaskListener listener;

    public interface AddingTaskListener {
        public void onTaskAdded();
        public void onTaskAddingCancel();
    }

    @Override
    public void onStart() {
        super.onStart();
        listener = (AddingTaskListener) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.done_task);

        View container = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_task, null);

        final TextInputLayout tilTitle = container.findViewById(R.id.tilDialogTaskTitle);
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

        ModelTask modelTask = new ModelTask();

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTime.length() == 0) {
                    etTime.setText(" ");
                }
                DialogFragment datePickerFragment = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int mounth, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, mounth);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        etDate.setText(Utils.getDAte(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        super.onCancel(dialog);
                        etTime.setText(null);
                    }
                };
                datePickerFragment.show(getFragmentManager(), "DatePickerFragment");
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTime.length() == 0) {
                    etDate.setText(" ");
                }
                DialogFragment timePickerFragment = new TimePickerFragment() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        calendar.set(Calendar.SECOND, 0);
                        etTime.setText(Utils.getTiem(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        super.onCancel(dialog);
                        etDate.setText(null);
                    }
                };
                timePickerFragment.show(getFragmentManager(), "TimePickerFragment");
            }
        });

        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onTaskAdded();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.Cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onTaskAddingCancel();
                dialogInterface.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final Button button = ((AlertDialog) dialogInterface).getButton(DialogInterface.BUTTON_POSITIVE);
                if (etTitle.length() == 0) {
                    button.setEnabled(false);
                    tilTitle.setError(getString(R.string.error_empty_text_file));
                }
                etTitle.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (etTitle.length() == 0) {
                            button.setEnabled(false);
                            tilTitle.setError(getString(R.string.error_empty_text_file));
                        } else {
                            button.setEnabled(true);
                            tilTitle.setError(null);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

        return dialog;
    }
}
