package satedshark.com.vk.mementomori;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Date;

public class Counter {
    public static
    DateTime birth = new DateTime(1976, 9, 15, 0, 0);
    DateTime now = new DateTime(DateTime.now());
    DateTime death = birth.plusYears(85);
    DateTime test = new DateTime(new Date());
    int days = Days.daysBetween(now, death).getDays();
    int sum = Days.daysBetween(birth, now).getDays();
}
