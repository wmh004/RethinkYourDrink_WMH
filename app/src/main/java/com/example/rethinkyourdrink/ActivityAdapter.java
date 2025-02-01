package com.example.rethinkyourdrink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<ActivityClass> activities;

    public ActivityAdapter(List<ActivityClass> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.individual_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityClass activity = activities.get(position);
        holder.tvDay.setText(activity.getDay());
        holder.tvBeverage.setText(activity.getBeverage());
        holder.tvAmount.setText(activity.getAmount() + " ml");
        holder.tvDescription.setText(activity.getDescription());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    // Method to update dataset dynamically
    public void setActivities(List<ActivityClass> activities) {
        this.activities = activities;
        notifyDataSetChanged();  // Refresh RecyclerView
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvBeverage, tvAmount, tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.TVDay);
            tvBeverage = itemView.findViewById(R.id.TVBeverage);
            tvAmount = itemView.findViewById(R.id.TVAmount);
            tvDescription = itemView.findViewById(R.id.TVDescription);
        }
    }
}
