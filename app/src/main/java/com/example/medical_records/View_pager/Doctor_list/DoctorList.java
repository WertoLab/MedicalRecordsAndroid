package com.example.medical_records.View_pager.Doctor_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.medical_records.DoctorList.Doctor;
import com.example.medical_records.DoctorList.DoctorAdapter;
import com.example.medical_records.DoctorList.SpecialtyAdapter;
import com.example.medical_records.R;
import com.example.medical_records.databinding.DoctorListBinding;

import java.util.ArrayList;

public class DoctorList extends Fragment {
    DoctorListBinding binding;
    RecyclerView rc;
    ArrayList<Doctor> doctors;
    DoctorAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DoctorListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc = binding.recyclerView;
        doctors = new ArrayList<>();
        adapter = new DoctorAdapter(doctors);
        ArrayList<String> specialties = new ArrayList<>();
        specialties.add("NEGR");
        Doctor d = new Doctor("Sergei Mikhaylin","Lyceum KFU",specialties);
        doctors.add(d);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }
}