package com.zjdt.dtsjwb.Activity.NewRequirements;


import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.R;

import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        //initialDate = (Date) getArguments().getSerializable(KEY_DATE);  //获取传过来的Date，用于确定初始的calendar
        Calendar calendar = Calendar.getInstance(Locale.CHINA); //获取China区Calendar实例，实际是GregorianCalendar的一个实例
        //calendar.setTime(initialDate); //初始化日期
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);  //获得当前日期所在月份有多少天（或者说day的最大值)，用于后面的计算

        Calendar calendarClone = (Calendar) calendar.clone(); //克隆一个Calendar再进行操作，避免造成混乱
        calendarClone.set(Calendar.DAY_OF_MONTH, 1);  //将日期调到当前月份的第一天
        int startDayOfWeek = calendarClone.get(Calendar.DAY_OF_WEEK); //获得当前日期所在月份的第一天是星期几
        calendarClone.set(Calendar.DAY_OF_MONTH, maxDay); //将日期调到当前月份的最后一天
        int endDayOfWeek = calendarClone.get(Calendar.DAY_OF_WEEK); //获得当前日期所在月份的最后一天是星期几

        /**
         * 计算上一个月在本月日历页出现的那几天.
         * 比如，startDayOfWeek = 3，表示当月第一天是星期二，所以日历向前会空出2天的位置，那么让上月的最后两天显示在星期日和星期一的位置上.
         */
        int startEmptyCount = startDayOfWeek - 1; //上月在本月日历页因该出现的天数。
        Calendar preCalendar = (Calendar) calendar.clone();  //克隆一份再操作
        preCalendar.set(Calendar.DAY_OF_MONTH, 1); //将日期调到当月第一天
        preCalendar.add(Calendar.DAY_OF_MONTH, -startEmptyCount); //向前推移startEmptyCount天
       /* for (int i = 0; i < startEmptyCount; i++) {
            DateInfo dateInfo = new DateInfo(); //使用DateInfo来储存所需的相关信息
            dateInfo.setDate(preCalendar.getTime());
            dateInfo.setType(DateInfo.PRE_MONTH); //标记日期信息的类型为上个月
            dateList.add(dateInfo); //将日期添加到数组中
            preCalendar.add(Calendar.DAY_OF_MONTH, 1); //向后推移一天
        }*/

        /**
         * 计算当月的每一天日期
         */
        calendar.set(Calendar.DAY_OF_MONTH, 1); //由于是获取当月日期信息，所以直接操作当月Calendar即可。将日期调为当月第一天
    /*    for (int i = 0; i < maxDay; i++) {
            DateInfo dateInfo = new DateInfo();
            dateInfo.setDate(calendar.getTime());
            dateInfo.setType(DateInfo.CURRENT_MONTH);  //标记日期信息的类型为当月
            dateList.add(dateInfo);
            calendar.add(Calendar.DAY_OF_MONTH, 1); //向后推移一天
        }
*/
        /**
         * 计算下月在本月日历页出现的那几天。
         * 比如，endDayOfWeek = 6，表示当月第二天是星期五，所以日历向后会空出1天的位置，那么让下月的第一天显示在星期六的位置上。
         */
        int endEmptyCount = 7 - endDayOfWeek; //下月在本月日历页上因该出现的天数
        Calendar afterCalendar = (Calendar) calendar.clone(); //同样，克隆一份在操作
      /*  for (int i = 0; i < endEmptyCount; i++) {
            DateInfo dateInfo = new DateInfo();
            dateInfo.setDate(afterCalendar.getTime());
            dateInfo.setType(DateInfo.AFTER_MONTH); //将DateInfo类型标记为下个月
            dateList.add(dateInfo);
            afterCalendar.add(Calendar.DAY_OF_MONTH, 1); //向后推移一天

        }*/
    }
}
