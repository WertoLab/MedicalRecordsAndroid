package com.example.medical_records.View_pager.Profile;
import static com.example.medical_records.MainActivity.width;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.medical_records.Career_oportunity.CareerOportunityAdapter;
import com.example.medical_records.Career_oportunity.Career_oportunity;
import com.example.medical_records.databinding.EditMedicalTrialBinding;
import com.example.medical_records.medical_topic.medical_topic;
import com.example.medical_records.R;
import com.example.medical_records.databinding.EditBasicInfoBinding;
import com.example.medical_records.databinding.FragmentProfileBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Profile extends Fragment {
    FragmentProfileBinding binding;
    int max_width;
    int edit_height;
    //profile
    CardView profile_cardview;
    TextView name;
    TextView place_of_living;
    TextView place_of_work;
    TextView medical_societies;
    Button edit_profile;
    //about
    TextView about;
    CardView about_cardview;
    TextView description;
    Button edit_description;
    String first,last,patr,cit,stat,countr;

    //Career properties
    Button edit_career_property;
    TextView current_career_property;
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
        place_of_living = binding.placeOfLiving;
        place_of_work = binding.placeOfWork;
        medical_societies = binding.medicalSocieties;
        edit_profile = binding.profileEdit;
        profile_cardview = binding.profileCardview;
        int color = profile_cardview.getCardBackgroundColor().getDefaultColor();
        edit_profile.setBackgroundColor(color);
        first = "Vasya";
        last = "Pupkin";
        patr = "Innekentich";
        name.setText(last + " " + first + " " + patr);

        //about initialization
        about_cardview = binding.aboutCardview;
        description = binding.shortDesc;
        about = binding.about;
        edit_description = binding.editAbout;
        edit_description.setBackgroundColor(color);
        description.setHint(getResources().getString(R.string.hint_about));
        description.setMaxWidth(max_width);
        edit_height = about.getLayoutParams().height;
        edit_profile.getLayoutParams().height = edit_height;
        edit_description.getLayoutParams().height = edit_height;
        edit_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                TextInputLayout layout = (TextInputLayout) getLayoutInflater().inflate(R.layout.custom_dialog_editabout,null);
                layout.getEditText().setText(description.getText().toString());
                layout.getEditText().setMinLines(1);
                b.setTitle("Edit about");
                b.setView(layout);
                b.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        description.setText(layout.getEditText().getText().toString());
                    }})
                    .setNegativeButton("Cancel",null)
                    .setNeutralButton("Clear text",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                            description.setText("");
                        }
                    })
                    .show();
            }
        });
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrollView layout = (ScrollView) getLayoutInflater().inflate(R.layout.edit_basic_info,null);
                EditBasicInfoBinding binding = EditBasicInfoBinding.bind(layout);
                EditText first_name = binding.firstNameEdittext; // *
                first_name.setText(first);
                EditText Last_name = binding.lastNameEdittext;//*
                Last_name.setText(last);
                EditText patronymic = binding.patronymicEdittext;
                patronymic.setText(patr);
                EditText country = binding.countryEdittext; // *
                country.setText(countr);
                EditText state = binding.stateEdittext;
                state.setText(stat);
                EditText city = binding.cityEdittext;
                city.setText(cit);
                EditText place_of_work_edittext= binding.placeOfWorkEdittext;
                place_of_work_edittext.setText(place_of_work.getText().toString());
                EditText med_societies = binding.specialtiesEdittext;
                med_societies.setText(medical_societies.getText().toString());
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setView(layout)
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean b = true;
                                first = first_name.getText().toString();
                                last = Last_name.getText().toString();
                                countr = country.getText().toString();
                                if(first.isEmpty()){
                                    b = false;
                                    first_name.setError("fill this field");
                                }
                                if(last.isEmpty()){
                                   b = false;
                                   Last_name.setError("fill this field");
                                }
                                if(countr.isEmpty()){
                                    b = false;
                                    country.setError("fill this field");
                                }
                                if(b){
                                    patr = patronymic.getText().toString();
                                    name.setText(last + " " + first + " " + patr);
                                    cit = city.getText().toString();
                                    stat = state.getText().toString();
                                    place_of_work.setText(place_of_work_edittext.getText().toString());
                                    String itog = "";
                                    if(!cit.isEmpty()) itog = cit + ",";
                                    if(!stat.isEmpty()) itog += stat + ",";
                                    itog += countr;
                                    place_of_living.setText(itog);
                                    medical_societies.setText(med_societies.getText().toString());
                                }

                            }
                        });
                b.show();
            }
        });
        //Interest
        edit_career_property = binding.editCareerOportunity;
        current_career_property = binding.currentCareerOportunity;
        ArrayList<Career_oportunity> arrayList = new ArrayList<>();
        arrayList.add(new Career_oportunity("Conducting Clinical Trials",getResources().getDrawable(R.drawable.medic)));
        arrayList.add(new Career_oportunity("Collaborating on Publications",getResources().getDrawable(R.drawable.publication)));
        arrayList.add(new Career_oportunity("Speaking at Congresses",getResources().getDrawable(R.drawable.public_speaking)));
        arrayList.add(new Career_oportunity("Continue Medical Education(CME)",getResources().getDrawable(R.drawable.school)));
        arrayList.add(new Career_oportunity("Obtaining Grants",getResources().getDrawable(R.drawable.grants)));
        CareerOportunityAdapter adapter = new CareerOportunityAdapter(arrayList,getContext());
        edit_career_property.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RecyclerView r = (RecyclerView) LayoutInflater.from(getContext()).inflate(R.layout.edit_career_oportunity_layout,null,false);
                r.setMinimumWidth((int) (width * 0.8));
                r.setAdapter(adapter);
                new AlertDialog.Builder(getContext())
                        .setView(r)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(adapter.getLast_pressed() != null && adapter.getLast_pressed_and_checked() != null) {
                                    Log.d("LOLKEK", adapter.getLast_pressed().isChecked() + " " + adapter.getLast_pressed_and_checked().isChecked());
                                    adapter.setLast_pressed();
                                }
                            }
                        })
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(adapter.getLast_pressed() != null && adapter.getLast_pressed_and_checked() != null)
                                    Log.d("LOLKEK", adapter.getLast_pressed().isChecked() + " " + adapter.getLast_pressed_and_checked().isChecked());
                                if (adapter.getLast_checked() != -1) {
                                    adapter.setLast_pressed_and_checked();
                                    current_career_property.setText(arrayList.get(adapter.getLast_checked()).getName());
                                    current_career_property.setCompoundDrawablesWithIntrinsicBounds(arrayList.get(adapter.getLast_checked()).getIcon(), null, null, null);
                                    Log.d("LOLKEK", adapter.getLast_pressed().isChecked() + " " + adapter.getLast_pressed_and_checked().isChecked());
                                }
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        //
        ArrayList<Pair<String,String>> a =  new ArrayList<Pair<String, String>>();
        a.add(new Pair<>("first","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        a.add(new Pair<>("second","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        a.add(new Pair<>("third","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        a.add(new Pair<>("forth","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        medical_topic medical_topic = new medical_topic(a, new com.example.medical_records.medical_topic.medical_topic.click_on_medical_topic() {
            @Override
            public void onclick(int position) {
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Changing medical topic");
                View vieww = getLayoutInflater().inflate(R.layout.edit_medical_trial,null,false);
                EditMedicalTrialBinding bin = EditMedicalTrialBinding.bind(vieww);
                b.setView(vieww);
                Log.d("LOLKEK",position + " " + a.get(position).first  + " " + a.get(position).second);
                EditText name = bin.nameEdittext;
                EditText url = bin.urlEdittext;
                name.setText(a.get(position).first);
                url.setText(a.get(position).second);
                b.setNegativeButton("Cancel",null);
                b.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!name.getText().toString().isEmpty() && !url.getText().toString().isEmpty())
                        a.set(position,new Pair<>(name.getText().toString(),url.getText().toString()));
                    }
                });
                b.show();
            }
        });
        RecyclerView rec = binding.medicalTopics;
        rec.setAdapter(medical_topic);

    }
}