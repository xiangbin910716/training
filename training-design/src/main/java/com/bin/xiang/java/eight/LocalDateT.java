package com.bin.xiang.java.eight;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LocalDateT {


    public static void main(String[] args) {
        LocalDate localDateT = LocalDate.now();
        System.out.println("今天的日期是：" + localDateT);
        System.out.println(localDateT.getYear()+"年");
        System.out.println(localDateT.getMonthValue()+"月");
        System.out.println(localDateT.getDayOfMonth()+"日");
        LocalDate localDate = LocalDate.of(2018,4,19);
        System.out.println("自定义日期"+localDate);
        System.out.println("判断日期相等："+localDateT.equals(localDate));
        ZoneId zone = ZoneId.systemDefault();

        System.out.println(getBetweenDay(Date.from(localDate.atStartOfDay().atZone(zone).toInstant()),Date.from(localDateT.atStartOfDay().atZone(zone).toInstant())));
    }

    /**
     * 判断相差几天
     * @param date1
     * @param date2
     * @return
     */
    public static int getBetweenDay(Date date1, Date date2) {
        Calendar d1 = new GregorianCalendar();
        d1.setTime(date1);
        Calendar d2 = new GregorianCalendar();
        d2.setTime(date2);
        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            do {
                System.out.println(d1.getActualMaximum(Calendar.DAY_OF_YEAR));
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }
}
