package satedshark.com.vk.mementomori;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {

    private static DatePickerFragment datePickerFragment;
    private DatePicker datePicker;

    public static DatePickerFragment newInstance(){
        if(datePickerFragment == null) datePickerFragment = new DatePickerFragment();
        return datePickerFragment;
    }

    public DatePickerFragment() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker, null);
        datePicker = v.findViewById(R.id.date_picker);
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_dialog_date).
                setPositiveButton(R.string.date_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
    }

}
