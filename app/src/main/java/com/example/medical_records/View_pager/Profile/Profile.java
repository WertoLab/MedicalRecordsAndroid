package com.example.medical_records.View_pager.Profile;
import static com.example.medical_records.MainActivity.height;
import static com.example.medical_records.MainActivity.width;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.medical_records.databinding.EditBasicInfoBinding;
import com.example.medical_records.databinding.FragmentProfileBinding;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

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
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                TextInputLayout layout = (TextInputLayout) getLayoutInflater().inflate(R.layout.custom_dialog_editabout,null);
                layout.getEditText().setText(description.getText().toString());
                layout.getEditText().setMinLines(3);
                b.setTitle("Edit about");
                b.setView(layout);
                b.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        description.setText(layout.getEditText().getText().toString());
                    }
                })
                    .setNegativeButton("Cancel",null)
                        .show();
            }
        });
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.edit_basic_info,null);
        EditBasicInfoBinding binding = EditBasicInfoBinding.bind(layout);
        EditText first_name = binding.firstNameEdittext; // *
        EditText Last_name = binding.lastNameEdittext;//*
        EditText patronymic = binding.patronymicEdittext;
        EditText country = binding.countryEdittext; // *
        EditText state = binding.stateEdittext;
        EditText city = binding.cityEdittext;
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setView(layout)
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean b = true;
                                String f_name = first_name.getText().toString(),last_n = Last_name.getText().toString(),countr = country.getText().toString();
                                if(f_name.isEmpty()){
                                    b = false;
                                    first_name.setError("fill this field");
                                }
                                if(last_n.isEmpty()){
                                   b = false;
                                   Last_name.setError("fill this field");
                                }
                                if(countr.isEmpty()){
                                    b = false;
                                    country.setError("fill this field");
                                }
                                if(b){

                                }

                            }
                        });
            }
        });
    }
}