package satedshark.com.vk.mementomori;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String DATE_BIRTHDAY = "day of birthday";
    private static final String DATE_DEATH = "day of death";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentSetting = fm.findFragmentById(R.id.fragment_container);
        if(fragmentSetting == null){
            fragmentSetting = SettingFragment.newInstance(editor);
            fm.beginTransaction().add(R.id.fragment_container, fragmentSetting, null).commit();
        }
    }
}
