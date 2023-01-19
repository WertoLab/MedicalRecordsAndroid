package com.example.medical_records.View_pager.Profile;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.example.medical_records.R;
import com.example.medical_records.databinding.FragmentProfileBinding;

import org.w3c.dom.Text;

public class Profile extends Fragment {
    FragmentProfileBinding binding;
    int x_left,x_right;
    int edit_height;
    //base information
    CardView profile_cardview;
    TextView name;
    TextView left_base;
    TextView about;
    TextView right_base;
    Button edit_profile;

    //about
    CardView about_cardview;
    TextView description;
    Button edit_descpription;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //base information initialization
        name = binding.name;
        left_base = binding.leftProfile;
        right_base = binding.rightProfile;
        edit_profile = binding.profileEdit;
        profile_cardview = binding.profileCardview;
        int color = profile_cardview.getCardBackgroundColor().getDefaultColor();
        edit_profile.setBackgroundColor(color);
        name.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                name.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int[] position = new int[2];
                int[] position2 = new int[2];
                left_base.getLocationOnScreen(position);
                right_base.getLocationOnScreen(position2);
                name.setMaxWidth(position2[0] - position[0]);
                x_left = position[0];
                x_right = position2[0];
            }
        });

        //about initialization
        about_cardview = binding.aboutCardview;
        description = binding.shortDesc;
        about = binding.about;
        edit_descpription = binding.editAbout;
        edit_descpription.setBackgroundColor(color);
        description.setHint(getResources().getString(R.string.hint_about));
        description.setMaxWidth(x_right - x_left);
        edit_height = about.getLayoutParams().height;
        edit_profile.getLayoutParams().height = edit_height;
        edit_descpription.getLayoutParams().height = edit_height;
    }
}