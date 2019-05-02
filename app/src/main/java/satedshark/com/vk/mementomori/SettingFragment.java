package satedshark.com.vk.mementomori;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.Date;

public class SettingFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_DATE_BIRTHDAY = 380;
    private static final int REQUEST_DATE_DEATH = 471;
    private static final String DATE_DIALOG = "date_dialog";

    private EditText editBirthday;
    private EditText editDeath;
    private Button buttonOk;
    private Button buttonCancel;
    private MainActivity activity;
    private static SettingFragment settingFragment;

    public SettingFragment() {
    }

    protected static SettingFragment newInstance() {
        if(settingFragment == null) settingFragment = new SettingFragment();
        return settingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        editBirthday = v.findViewById(R.id.editDateBirthday);
        editDeath = v.findViewById(R.id.editDateDeath);
        buttonOk = v.findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener((View.OnClickListener)getActivity());
        buttonCancel = v.findViewById(R.id.buttonCancel);
        editBirthday.setOnClickListener(this);
        editDeath.setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editDateBirthday: showDatePicker(REQUEST_DATE_BIRTHDAY);
            break;
            case R.id.editDateDeath: showDatePicker(REQUEST_DATE_DEATH);
            break;
        }
    }

    private void showDatePicker(int requestCode){
        FragmentManager fragmentManager = getFragmentManager();
        DatePickerFragment dialog = DatePickerFragment.newInstance();
        dialog.setTargetFragment(SettingFragment.this, requestCode);
        dialog.show(fragmentManager, DATE_DIALOG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

        switch (requestCode){
            case REQUEST_DATE_BIRTHDAY:
                editBirthday.setText(DateFormat.format("d MMMM yyyy", date));
                activity.saveDateOfBirthday(date.getTime());
            break;
            case REQUEST_DATE_DEATH:
                editDeath.setText(DateFormat.format("d MMMM yyyy", date));
                activity.saveDateOfDeath(date.getTime());
            break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected interface PreferencesSaver{
        void saveDateOfBirthday(long date);
        void saveDateOfDeath(long date);
    }
}
