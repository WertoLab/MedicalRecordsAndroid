package com.example.medical_records;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;

import com.example.medical_records.View_pager.Adapter;
import com.example.medical_records.View_pager.Doctor_list.DoctorList;
import com.example.medical_records.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    public static int height;
    public static int width;
    ActivityMainBinding binding;
    ViewPager viewPager;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        getSupportActionBar().setTitle("");
        Display d = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        Adapter adapter = new Adapter(getSupportFragmentManager());
        viewPager = binding.viewPager;
        navigationView = binding.bottomNavigation;
        navigationView.setOnNavigationItemSelectedListener(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.profile).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.issues).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.doctolist).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        d.getSize(size);
        height = size.y;
        width = size.x;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.profile:
                viewPager.setCurrentItem(0,true);
                break;
            case R.id.issues:
                viewPager.setCurrentItem(1,true);
                break;
            case R.id.doctolist:
                viewPager.setCurrentItem(2,true);
        }
        return true;
    }
}