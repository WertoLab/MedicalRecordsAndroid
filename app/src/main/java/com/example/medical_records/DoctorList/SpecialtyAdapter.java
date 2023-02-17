package com.example.medical_records.DoctorList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_records.R;
import com.example.medical_records.databinding.SpecialtyItemLayoutBinding;

import java.util.ArrayList;
import java.util.Locale;

public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.ViewHolder> {
    SpecialtyItemLayoutBinding binding;
    ArrayList<String> strings;
    public SpecialtyAdapter(ArrayList<String> strings){
        this.strings = strings;
        Log.d("LOLKEK", String.valueOf(this.strings.size()));
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialty_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = strings.get(position);
        holder.bind(s);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SpecialtyItemLayoutBinding.bind(itemView);
            text = binding.textview;
        }
        public void bind(String s){
            text.setText(s);
        }
    }
}
