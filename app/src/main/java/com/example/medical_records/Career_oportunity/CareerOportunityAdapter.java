package com.example.medical_records.Career_oportunity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_records.R;
import com.example.medical_records.databinding.CareerOportunityLayoutBinding;

import java.util.ArrayList;

public class CareerOportunityAdapter extends RecyclerView.Adapter<CareerOportunityAdapter.Holder>{
    ArrayList<Career_oportunity> arrayList;
    Context context;
    private int last_checked = -1;
    public int last_checked_and_save = -1;
    private CheckBox last_pressed = null;
    private CheckBox last_pressed_and_checked = null;
    public CareerOportunityAdapter(ArrayList<Career_oportunity> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.career_oportunity_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Career_oportunity c = arrayList.get(position);
        holder.bind(c);
        holder.itemView.setTag(position);
        if(position == last_checked_and_save){
            holder.textView.setChecked(true);
            last_pressed = holder.textView;
        }
    }
    public int getLast_checked(){return last_checked;}
    public void setLast_pressed(){
        last_pressed.setChecked(false);
        last_pressed_and_checked.setChecked(true);
        last_pressed = last_pressed_and_checked;
        last_checked = last_checked_and_save;
    }
    public void setLast_pressed_and_checked(){
        last_checked_and_save = last_checked;
        last_pressed_and_checked = last_pressed;
    }

    public CheckBox getLast_pressed() {
        return last_pressed;
    }

    public CheckBox getLast_pressed_and_checked() {
        return last_pressed_and_checked;
    }

    class Holder extends RecyclerView.ViewHolder{
        private final CheckBox textView;
        private final CareerOportunityLayoutBinding binding;
        public Holder(@NonNull View itemView) {
            super(itemView);
            binding = CareerOportunityLayoutBinding.bind(itemView);
            textView = binding.textview;
        }
        public void bind(Career_oportunity c){
            textView.setText(c.getName());
            textView.setChecked(false);
            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(last_checked != -1){
                        last_pressed.setChecked(false);
                    }
                    last_checked = (int) itemView.getTag();
                    last_pressed = textView;
                }
            });
        }
    }

}
