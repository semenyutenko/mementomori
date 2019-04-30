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

import java.util.Date;

public class SettingFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_DATE_BIRTHDAY = 380;
    private static final int REQUEST_DATE_DEATH = 471;
    private static final String DATE_DIALOG = "date_dialog";

    private static SettingFragment settingFragment;

    private EditText editBirthday;
    private EditText editDeath;
    private Button buttonOk;
    private Button buttonCancel;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SettingFragment() {
    }

    public static SettingFragment newInstance(SharedPreferences preferences) {
        if (settingFragment == null) settingFragment = new SettingFragment();
        settingFragment.preferences = preferences;
        settingFragment.editor = settingFragment.preferences.edit();
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
        if(preferences.contains(MainActivity.DATE_BIRTHDAY)){
            Date date = new Date(preferences.getLong(MainActivity.DATE_BIRTHDAY, 0));
            editBirthday.setText(DateFormat.format("d MMMM yyyy", date));
        }
        editDeath = v.findViewById(R.id.editDateDeath);
        if(preferences.contains(MainActivity.DATE_DEATH)){
            Date date = new Date(preferences.getLong(MainActivity.DATE_DEATH, 0));
            editDeath.setText(DateFormat.format("d MMMM yyyy", date));
        }
        buttonOk = v.findViewById(R.id.buttonOk);
        buttonCancel = v.findViewById(R.id.buttonCancel);
        editBirthday.setOnClickListener(this);
        editDeath.setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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
            case REQUEST_DATE_BIRTHDAY: editBirthday.setText(DateFormat.format("d MMMM yyyy", date));
            editor.putLong(MainActivity.DATE_BIRTHDAY, date.getTime()).commit();
            break;
            case REQUEST_DATE_DEATH: editDeath.setText(DateFormat.format("d MMMM yyyy", date));
            editor.putLong(MainActivity.DATE_DEATH, date.getTime()).commit();
            break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
