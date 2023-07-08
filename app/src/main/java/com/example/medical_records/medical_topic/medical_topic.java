package com.example.medical_records.medical_topic;

import android.content.Intent;
import android.net.Uri;
import android.text.util.Linkify;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_records.R;
import com.example.medical_records.databinding.MedicalTopicElementLayoutBinding;

import java.util.ArrayList;

public class medical_topic extends RecyclerView.Adapter<medical_topic.ViewHolder>{
    ArrayList<Pair<String,String>> arrayList;
    click_on_medical_topic click;
    public medical_topic(ArrayList<Pair<String,String>>arrayList,click_on_medical_topic click){
        this.arrayList = arrayList;
        this.click = click;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_topic_element_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String,String> l = arrayList.get(position);
        holder.itemView.setTag(position);
        holder.bind(l);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        private final MedicalTopicElementLayoutBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MedicalTopicElementLayoutBinding.bind(itemView);
            textView = binding.textViewMedicalTopic;
            textView.setAutoLinkMask(Linkify.WEB_URLS);
            textView.setLinksClickable(true);
        }
        public void bind(Pair<String,String> l){
            textView.setText(l.first);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(l.second));
                    v.getContext().startActivity(intent);
                }
            });
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    click.onclick((Integer) itemView.getTag());
                    return false;
                }
            });
        }
    }

    public interface click_on_medical_topic{
        void onclick(int position);
    }
}

