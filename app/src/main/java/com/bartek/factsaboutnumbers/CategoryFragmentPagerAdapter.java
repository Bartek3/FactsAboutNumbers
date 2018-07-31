package com.bartek.factsaboutnumbers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bartek on 2018-07-31.
 */

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;

    private String tabTitles[] = new String[] { "Number", "Year", "Date", "Math" };
    private Context mContext;

    public CategoryFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CategoryFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumberFragment();
        } else if (position == 1) {
            return new YearFragment();
        } else if (position == 2) {
            return new DateFragment();
        } else {
            return new MathFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getResources().getString(R.string.category_number);
        } else if (position == 1) {
            return mContext.getResources().getString(R.string.category_year);
        } else if (position == 2) {
            return mContext.getResources().getString(R.string.category_date);
        } else {
            return mContext.getResources().getString(R.string.category_math);
        }
    }

}
