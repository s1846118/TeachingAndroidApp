package com.example.present;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LearningDisabilityAdapter  extends RecyclerView.Adapter<LearningDisabilityAdapter.LDViewHolder> {

    Context context;
    ArrayList<LearningDisabilityModel> learingModels;

    public LearningDisabilityAdapter(Context context, ArrayList<LearningDisabilityModel> learingModels){
        this.context = context;
        this.learingModels = learingModels;
    }

    @NonNull
    @Override
    public LearningDisabilityAdapter.LDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.learn_layout, parent);

        return new LearningDisabilityAdapter.LDViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningDisabilityAdapter.LDViewHolder holder, int position) {
        //assigning values to each row
        holder.title.setText(learingModels.get(position).getName());
        holder.imageView.setImageResource(learingModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return learingModels.size();
    }


    public static class LDViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        //grabbing views from layout file - similar to oncreate
        public LDViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            title = itemView.findViewById(R.id.title);

        }
    }
}
