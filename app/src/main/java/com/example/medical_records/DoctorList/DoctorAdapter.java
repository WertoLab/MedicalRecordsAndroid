package com.example.medical_records.DoctorList;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_records.R;
import com.example.medical_records.databinding.DoctorItemLayoutBinding;

import org.w3c.dom.Text;

import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private ArrayList<Doctor> doctors;
    private DoctorItemLayoutBinding binding;
    public DoctorAdapter(ArrayList<Doctor> doctors){
        this.doctors = doctors;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_layout,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.bind(doctor);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView name;
        private TextView place_of_work;
        private TextView left;
        private TextView right;
        private RecyclerView rc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DoctorItemLayoutBinding.bind(itemView);
            cardView = binding.cardview;
            name = binding.name;
            place_of_work = binding.placeOfWork;
            rc = binding.rc;
            left = binding.left;
            right = binding.right;
            rc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    rc.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int[] position = new int[2];
                    int[] position2 = new int[2];
                    left.getLocationOnScreen(position);
                    right.getLocationOnScreen(position2);
                    Log.d("LOLKEK",position[0] + " " + position2[0]);
                    rc.setMinimumWidth(position2[0] - position[0]);
                    rc.getLayoutParams().width = position2[0] - position[0];
                    name.setMaxWidth(position2[0] - position[0]);
                    place_of_work.setMaxWidth(position2[0] - position[0]);
                }
            });
        }
        public void bind(Doctor doctor){
            rc.setAdapter(doctor.getAdapter());
            rc.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            name.setText(doctor.getName());
            place_of_work.setText(doctor.getCompany());
        }
    }
}
