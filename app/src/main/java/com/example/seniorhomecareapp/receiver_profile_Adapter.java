package com.example.seniorhomecareapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class receiver_profile_Adapter extends RecyclerView.Adapter<receiver_profile_Adapter.ViewHolder> {

    private List<receiver_profile> profiles;  // Use the correct type here
    private List<receiver_profile> selectedProfiles;

    public receiver_profile_Adapter(List<receiver_profile> profiles) {
        this.profiles = profiles;
        this.selectedProfiles = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_receiver_profile_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        receiver_profile profile = profiles.get(position);  // Use the correct type here
        holder.nameTextView.setText(profile.getName());
        holder.timeTextView.setText(profile.getTime());
        holder.locationTextView.setText(profile.getLocation());
        holder.bioTextView.setText(profile.getBio());
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the profiles list
        return profiles.size();
    }

    public List<receiver_profile> getSelectedProfiles() {
        return selectedProfiles;
    }

    // ViewHolder class to hold references to the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView bioTextView;
        TextView timeTextView;
        TextView locationTextView;
        ImageView likeButton;
        ImageView starImageView;
        boolean isHighlighted = false;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            timeTextView = itemView.findViewById(R.id.time);
            locationTextView = itemView.findViewById(R.id.location);
            bioTextView = itemView.findViewById(R.id.description);
            likeButton = itemView.findViewById(R.id.likeButton);
            starImageView = itemView.findViewById(R.id.starImageView);
            if (likeButton != null) {
                likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Toggle the highlighted state
                        isHighlighted = !isHighlighted;

                        // Update the image based on the highlighted state
                        if (isHighlighted) {
                            likeButton.setImageResource(R.drawable.save);
                        } else {
                            likeButton.setImageResource(R.drawable.unsave);
                        }
                    }
                });
            }
        }
    }
}