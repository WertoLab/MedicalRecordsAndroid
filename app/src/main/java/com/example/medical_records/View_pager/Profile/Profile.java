package com.example.medical_records.View_pager.Profile;
import static com.example.medical_records.MainActivity.height;
import static com.example.medical_records.MainActivity.width;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.medical_records.R;
import com.example.medical_records.databinding.CustomDialogEditaboutBinding;
import com.example.medical_records.databinding.FragmentProfileBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;
import java.util.TimerTask;

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
        edit_descpription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom_dialog d = new custom_dialog(getContext(),1,(int)(height * 0.2),(int)(width * 0.8));
                d.getWindow().setLayout((int)(width * 0.8),(int)(height * 0.2));
                d.show();
            }
        });
    }
    class custom_dialog extends Dialog implements View.OnClickListener{
        int number = -1;
        int height = 0,width = 0;
        //1,2
        TextView textView;
        EditText editAboutEditText;
        TextInputLayout editAboutLayout;
        CustomDialogEditaboutBinding binding_custom;
        Button cancel;
        Button save;

        //
        private static final int edit_about = 1;
        private static final int add_publication = 2;
        private static final int add_medic_topics = 3;
        private static final int edit_basic_information = 4;
        private static final int edit_career_oportunity = 5;
        public custom_dialog(@NonNull Context context,int number,int height,int width) {
            super(context);
            this.number = number;
            this.height = height;
            this.width = width;
        }

        public custom_dialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
        }

        protected custom_dialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.custom_dialog_editabout);
            binding_custom = CustomDialogEditaboutBinding.inflate(getLayoutInflater());
            save = binding_custom.saveAbout;
            editAboutEditText = binding_custom.editAboutEdittext;
            editAboutLayout = binding_custom.editAboutLayout;
            editAboutLayout.getLayoutParams().width = (int)(width * 0.7);
            editAboutLayout.setMaxWidth((int) (width * 0.7));
            editAboutLayout.setMinWidth((int)(width * 0.7));
            cancel = binding_custom.cancelAbout;
            save.setOnClickListener(this);
            cancel.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int id = view.getId();
            if(number < 3){
                switch (id){
                    case R.id.save_about:
                        edit_descpription.setText(editAboutEditText.getText());
                        hide();
                        break;
                    case R.id.cancel_about:
                        hide();
                        break;
                }
            }
        }
    }
}