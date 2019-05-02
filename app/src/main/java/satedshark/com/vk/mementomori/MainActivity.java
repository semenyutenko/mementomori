package satedshark.com.vk.mementomori;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String DATE_BIRTHDAY = "day_of_birthday";
    private static final String DATE_DEATH = "day_of_death";
    private static final String SETTINGS_OR_CARD = "settings_or_card";

    private int pastTime;
    private int timeLeft;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private FragmentManager fm;
    private DateTime today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getPreferences(MODE_PRIVATE);
        editor = preferences.edit();
        today = new DateTime(DateTime.now());
        fm = getSupportFragmentManager();
        if (!preferences.contains(DATE_BIRTHDAY) && !preferences.contains(DATE_DEATH)) callSettings();
        else {
            Fragment fragment = fm.findFragmentById(R.id.fragment_container);
            if((fragment == null) || (fragment instanceof CardFragment)) callCard();
            else callSettings();
        }
    }
    public void saveDateOfBirthday(long date){
        editor.putLong(MainActivity.DATE_BIRTHDAY, date).commit();
    }
    public void saveDateOfDeath(long date){
        editor.putLong(MainActivity.DATE_DEATH, date).commit();
    }

    private void callCard() {
        DateTime dateBirthday = new DateTime(preferences.getLong(DATE_BIRTHDAY, 0));
        DateTime dateDeath = new DateTime(preferences.getLong(DATE_DEATH, 0));
        pastTime = Days.daysBetween(dateBirthday, today).getDays();
        timeLeft = Days.daysBetween(today, dateDeath).getDays();

        Fragment fragmentCard = CardFragment.newInstance(pastTime, timeLeft);
        fm.beginTransaction().replace(R.id.fragment_container, fragmentCard, null).commit();
    }

    private void callSettings() {
            Fragment fragmentSetting = SettingFragment.newInstance(preferences.getLong(DATE_BIRTHDAY, today.getMillis()),
                    preferences.getLong(DATE_DEATH, today.getMillis()));
            fm.beginTransaction().replace(R.id.fragment_container, fragmentSetting, null).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSettings:
                callSettings();
                break;
            case R.id.buttonOk:
                callCard();
                break;
        }

    }
}
