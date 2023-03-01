package com.example.medical_records.View_pager;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.medical_records.R;
import com.example.medical_records.View_pager.Doctor_list.DoctorList;
import com.example.medical_records.View_pager.Issues.Issues;
import com.example.medical_records.View_pager.Profile.Profile;

public class  Adapter extends FragmentStatePagerAdapter {
    public Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    public Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Profile();
            case 1:
                return new Issues();
            case 2:
                return new DoctorList();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
