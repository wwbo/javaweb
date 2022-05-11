package com.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WorkDays {
    public static int getWorkDays(int theYear, int theMonth) {
        // 计算指定月有多少工作日
        int workDays = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(theYear, theMonth - 1, 1);// 从每月1号开始
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < days; i++) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                workDays++;
            }
            cal.add(Calendar.DATE, 1);
        }
        return workDays;
    }
    public static int getDays(String start, String end) throws Exception{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(start);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        int dd = date1.getDay();
        Long day = (date2.getTime()-date1.getTime())/(1000*60*60*24) + 1;
        int weekEnds = 0;
        for (int i=0; i<day; i++){
            if (dd == 6 || dd == 0){
                weekEnds ++;
            }
            long a = date1.getTime();
            a += 1000 * 60 *60 *24 * (i+1);
            dd = new Date(a).getDay();
        }
        int days = Integer.valueOf(String.valueOf(day - weekEnds));
        return days;
    }
    public static String getLastDayOfMonth(String yearmonth)
    {
        Calendar cal = Calendar.getInstance();
        int year = Integer.valueOf(yearmonth.substring(0,4));
        int month = Integer.valueOf(yearmonth.substring(5,7));
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }
    public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }
}
