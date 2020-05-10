package tian.pusen.jdk8.date;

import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneTest {
    public static void main(String[] args) {
        System.out.println("Time zone ");
        System.out.println("Zone size:" + ZoneId.getAvailableZoneIds().size());
//        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);

//        System.out.println(TimeZone.getDefault());
//        clock();
//        System.out.println(new Date());

        System.out.println("时间一旦使用后，无法再设置了");
        System.setProperty("user.timezone", "America/New_York");
        System.out.println(TimeZone.getDefault());
        clock();
        System.out.println(new Date());

        TimeZone.setDefault(TimeZone.getTimeZone("Japan"));
        System.out.println(TimeZone.getDefault());
        clock();
        System.out.println(new Date());

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(TimeZone.getDefault());
        clock();
        System.out.println(new Date());
    }
    private static void clock() {
        // 替换 System.currentTimeMillis()
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);   // legacy java.util.Date
        System.out.println(legacyDate);
    }
}
