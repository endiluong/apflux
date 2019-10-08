package com.oldbie.applux.adapter;

import android.content.Context;

import com.oldbie.applux.R;
import com.oldbie.applux.fragment.FragmentHome;
import com.oldbie.applux.fragment.FragmentTimetable;
import com.oldbie.applux.fragment.FragmentUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;
    public FragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super( fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
            this.context=context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentTimetable();
            case 2:
                return new FragmentUser();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString( R.string.home);
            case 1:
                return context.getString( R.string.timetable);
            case 2:
                return context.getString( R.string.user);
        }

        return super.getPageTitle( position );
    }
    @Override
    public int getCount() {
        return 3;
    }

}
