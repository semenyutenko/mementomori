package satedshark.com.vk.mementomori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SettingFragment extends Fragment implements View.OnClickListener {

    private static SettingFragment settingFragment;

    private EditText eidtBirthday;
    private EditText editDeath;
    private Button buttonOk;
    private Button buttonCancel;
    private SharedPreferences.Editor editor;

    public SettingFragment() {
    }

    public static SettingFragment newInstance(SharedPreferences.Editor editor) {
        if (settingFragment == null) settingFragment = new SettingFragment();
        settingFragment.editor = editor;
        return settingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        eidtBirthday = v.findViewById(R.id.editDateBirthday);
        editDeath = v.findViewById(R.id.editDateDeath);
        buttonOk = v.findViewById(R.id.buttonOk);
        buttonCancel = v.findViewById(R.id.buttonCancel);
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

    }


}
