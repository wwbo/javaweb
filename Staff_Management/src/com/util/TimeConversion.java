package com.util;

import com.pojo.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeConversion {
    public static void timetoString(List<Employee> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getHiredate() != null){
                Date date = list.get(i).getHiredate();
                String hiretime = dateFormat.format(date);
                list.get(i).setDate(hiretime);
            }
        }
    }
    public static void birthtimetoString(List<Employee> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getBirthday() != null){
                Date date = list.get(i).getBirthday();
                String bithdate = dateFormat.format(date);
                list.get(i).setBirthdate(bithdate);
            }
        }
    }
    public static void overtimetoString(List<OverTime> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getEndtime() != null && list.get(i).getStarttime() != null){
                Date starttime = list.get(i).getStarttime();
                Date endtime = list.get(i).getEndtime();
                Date filltime = list.get(i).getFilldate();
                String start = dateFormat.format(starttime);
                String end = dateFormat.format(endtime);
                String fill = dateFormat.format(filltime);
                list.get(i).setStart(start);
                list.get(i).setEnd(end);
                list.get(i).setFill(fill);
            }
        }
    }
    public static void leavetimetoString(List<Leave> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getEndtime() != null && list.get(i).getStarttime() != null){
                Date starttime = list.get(i).getStarttime();
                Date endtime = list.get(i).getEndtime();
                Date filltime = list.get(i).getFilldate();
                String start = dateFormat.format(starttime);
                String end = dateFormat.format(endtime);
                String fill = dateFormat.format(filltime);
                list.get(i).setStart(start);
                list.get(i).setEnd(end);
                list.get(i).setFill(fill);
            }
        }
    }
    public static void traveltimetoString(List<Travel> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getEndtime() != null && list.get(i).getStarttime() != null){
                Date starttime = list.get(i).getStarttime();
                Date endtime = list.get(i).getEndtime();
                Date filltime = list.get(i).getFilldate();
                String start = dateFormat.format(starttime);
                String end = dateFormat.format(endtime);
                String fill = dateFormat.format(filltime);
                list.get(i).setStart(start);
                list.get(i).setEnd(end);
                list.get(i).setFill(fill);
            }
        }
    }
    public static void punchClocktoString(List<PunchClock> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getPunch_inTime() != null){
                Date intime = list.get(i).getPunch_inTime();
                String in = dateFormat.format(intime);
                list.get(i).setInTime(in);
            }
            if (list.get(i).getPunch_outTime() != null){
                Date outime = list.get(i).getPunch_outTime();
                String out = dateFormat.format(outime);
                list.get(i).setOutTime(out);
            }
        }
    }
    public static void AttendSettingtoString(List<AttendanceSetting> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        for (int i=0; i<list.size(); i++){
            Date start = list.get(i).getStarttime();
            Date end = list.get(i).getEndtime();
            Date latest = list.get(i).getLateststarttime();
            list.get(i).setStart(dateFormat.format(start));
            list.get(i).setLatest(dateFormat.format(latest));
            list.get(i).setEnd(dateFormat.format(end));
        }
    }
    public static void DeparttimeotString(List<Depart> list){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<list.size(); i++){
            Date estab = list.get(i).getEstablished();
            list.get(i).setEstab(format.format(estab));
        }
    }
    public static void Announcement(List<Announcement> list){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<list.size(); i++){
            Date time = list.get(i).getReleaseTime();
            list.get(i).setTime(format.format(time));
        }
    }
}
