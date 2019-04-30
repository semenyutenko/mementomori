package satedshark.com.vk.mementomori;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    protected static final String DATE_BIRTHDAY = "day_of_birthday";
    protected static final String DATE_DEATH = "day_of_death";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentSetting = fm.findFragmentById(R.id.fragment_container);
        if(fragmentSetting == null){
            fragmentSetting = SettingFragment.newInstance(preferences);
            fm.beginTransaction().add(R.id.fragment_container, fragmentSetting, null).commit();
        }
    }
}
