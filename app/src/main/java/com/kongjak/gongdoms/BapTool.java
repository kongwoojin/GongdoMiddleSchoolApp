package com.kongjak.gongdoms;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import toast.library.meal.MealLibrary;

/**
 * Created by whdghks913 on 2015-02-17.
 */
public class BapTool {
    public static final String BAP_PREFERENCE_NAME = "BapData";

    public static final int TYPE_LUNCH = 1;

    public static String getBapStringFormat(int year, int month, int day, int type) {
        /**
         * Format : year-month-day-TYPE
         */
        return year + "-" + month + "-" + day + "-" + type;
    }

    /**
     * Pref Name Format : 2015-02-17-TYPE_index
     * ex) 2015-02-17-1_3
     */
    public static void saveBapData(Context mContext, String[] Calender, String[] Lunch) {
        /**
         * Do not Edit : yyyy.MM.dd(E)
         */
        Preference mPref = new Preference(mContext, BAP_PREFERENCE_NAME);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.MM.dd(E)", Locale.KOREA);

        for (int index = 0; index < Calender.length; index++) {
            try {
                Calendar mDate = Calendar.getInstance();
                mDate.setTime(mFormat.parse(Calender[index]));

                int year = mDate.get(Calendar.YEAR);
                int month = mDate.get(Calendar.MONTH) +1;
                int day = mDate.get(Calendar.DAY_OF_MONTH);

                String mPrefLunchName = getBapStringFormat(year, month, day, TYPE_LUNCH);

                String mLunch = Lunch[index];

                if (!MealLibrary.isMealCheck(mLunch)) mLunch = "";
                mPref.putString(mPrefLunchName, mLunch);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        }
    }

    /**
     * Format : 2015-2-11-2
     */
    public static restoreBapDateClass restoreBapData(Context mContext, int year, int month, int day) {
        /**
         * TODO 디버그를 하려면 Log.d를 활성화 하세요.
         */

        /**
         * TODO 원하는 Date Format이 있으면 'yyyy년 MM월 dd일', 'E요일'을 수정하세요
         */
        Preference mPref = new Preference(mContext, BAP_PREFERENCE_NAME);
        Preference mPref1 = new Preference(mContext, BAP_PREFERENCE_NAME);
        SimpleDateFormat mCalenderFormat = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
        SimpleDateFormat mDayOfWeekFormat = new SimpleDateFormat("E요일", Locale.KOREA);
        Calendar mDate = Calendar.getInstance();
        mDate.set(year, month, day);

        Log.d("YEAR", "" + mDate.get(Calendar.YEAR));
        Log.d("MONTH", "" + mDate.get(Calendar.MONTH));
        Log.d("DAY_OF_MONTH", "" + mDate.get(Calendar.DAY_OF_MONTH));

        restoreBapDateClass mData = new restoreBapDateClass();

        String mPrefLunchName = getBapStringFormat(year, month + 1, day, TYPE_LUNCH);

        Log.d("mPrefLunchName", "" + mPrefLunchName);

        mData.Calender = mCalenderFormat.format(mDate.getTime());
        mData.DayOfTheWeek = mDayOfWeekFormat.format(mDate.getTime());
        mData.Lunch = mPref1.getString(mPrefLunchName, null);
        Log.d("mData.Calender", "" + mData.Calender);
        Log.d("mData.DayOfTheWeek", "" + mData.DayOfTheWeek);
        Log.d("mData.Lunch", "" + mData.Lunch);
        /**
         * TODO 아침 데이터를 체크하는 코드는 필요 없다는 판단하에 추가하지 않음
         * 점심과 저녁이 null이 아니라면 아침도 null이 아니므로
         */
        if (mData.Lunch == null) {
            mData.isBlankDay = true;
        }

        return mData;
    }

    /**
     * restoreBapData()를 실행할때 반환하는 Class입니다.
     * Calender, DayOfTheWeek 에는 날짜 정보가, Lunch, Dinner에는 급식 데이터가 담겨있고
     * isBlankDay=true 이면 그 날은 급식 데이터가 저장되어 있지 않습니다.
     */
    public static class restoreBapDateClass {
        public String Calender;
        public String DayOfTheWeek;
        public String Lunch;
        public boolean isBlankDay = false;    }

    public static boolean mStringCheck(String mString) {
        if (mString == null || "".equals(mString) || " ".equals(mString))
            return true;
        return false;
    }
}
