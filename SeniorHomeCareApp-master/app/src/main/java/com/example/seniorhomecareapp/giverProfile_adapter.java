package com.example.seniorhomecareapp;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class giverProfile_adapter extends RecyclerView.Adapter<giverProfile_adapter.ViewHolder> {
    private List<Profile> profiles;
    private List<Profile> selectedProfiles;
    private DatabaseReference databaseReference;

    public giverProfile_adapter(List<Profile> profiles,DatabaseReference databaseReference) {
        this.profiles= profiles;
        this.selectedProfiles = new ArrayList<>();
        this.databaseReference = databaseReference;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_giver_profile_adapter, parent, false);
        return new ViewHolder(view,databaseReference);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to each item in the RecyclerView
        Profile profile = profiles.get(position);
        holder.bind(profile);
        holder.nameTextView.setText(profile.getName());
        holder.timeTextView.setText(profile.getTime());
        holder.locationTextView.setText(profile.getLocation());
        holder.bioTextView.setText(profile.getBio());
        int rating = profile.getRating();
        holder.certifiedBadge.setVisibility(profile.getCertified() ? View.VISIBLE : View.GONE);
        holder.verifiedBadge.setVisibility(profile.getVerified() ? View.VISIBLE : View.GONE);
        //boolean cert = profile.getCertified();
        //boolean verify = profile.getVerified()

        switch (rating) {
            case 0:
                holder.starImageView.setImageResource(R.drawable.review_stars_0);
                break;
            case 1:
                holder.starImageView.setImageResource(R.drawable.rating_stars_1);
                break;
            case 2:
                holder.starImageView.setImageResource(R.drawable.rating_stars_2);
                break;
            case 3:
                holder.starImageView.setImageResource(R.drawable.rating_stars_3);
                break;
            case 4:
                holder.starImageView.setImageResource(R.drawable.rating_stars_4);
                break;
            case 5:
                holder.starImageView.setImageResource(R.drawable.rating_stars_5);
                break;
            default:
                // Handle invalid rating
                break;
        }
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the filteredProfiles list
        return profiles.size();
    }

    public List<Profile> getSelectedProfiles() {
        return selectedProfiles;
    }

    /*
    public void applyFilter(String locationFilter, String bioFilter) {
        filteredProfiles.clear();

        // Convert location to lowercase for case-insensitive matching
        String lowercaseLocationFilter = locationFilter.toLowerCase();

        for (Profile profile : originalProfiles) {
            String lowercaseLocation = profile.getLocation().toLowerCase();
                filteredProfiles.add(profile);

            if (lowercaseLocation.contains(lowercaseLocationFilter) && profile.getBio().contains(bioFilter)) {
                filteredProfiles.add(profile);
            }
        }

        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }

     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView bioTextView;
        TextView timeTextView;
        TextView locationTextView;
        ImageView likeButton;
        ImageView starImageView;
        ImageView verifiedBadge;
        ImageView certifiedBadge;
        private DatabaseReference databaseReference;
        boolean isHighlighted = false;
        private Profile currentProfile;
        ViewHolder(View itemView,DatabaseReference databaseReference) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            timeTextView = itemView.findViewById(R.id.time);
            locationTextView = itemView.findViewById(R.id.location);
            bioTextView = itemView.findViewById(R.id.description);
            likeButton = itemView.findViewById(R.id.likeButton);
            starImageView = itemView.findViewById(R.id.starImageView);
            verifiedBadge = itemView.findViewById(R.id.verifiedBadge);
            certifiedBadge = itemView.findViewById(R.id.certifiedBadge);
            this.databaseReference = databaseReference;
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), review.class);

                    intent.putExtra("profileName", currentProfile.getName());
                    view.getContext().startActivity(intent);
                }
            });
            if (likeButton != null) {
                likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Toggle the highlighted state
                        isHighlighted = !isHighlighted;
                        databaseReference.child("profiles").push().setValue(currentProfile);
                        Intent intent= new Intent(view.getContext(),review.class);
                        intent.putExtra("profileName",currentProfile.getName());
                        view.getContext().startActivity(intent);

                        // Update the image based on the highlighted state
                        if (isHighlighted) {
                            likeButton.setImageResource(R.drawable.save);
                            databaseReference.child("profiles");
                        } else {
                            likeButton.setImageResource(R.drawable.savelist_uncheck_button);
                            databaseReference.child("profiles").removeValue();
                        }
                    }
                });
            }
        }
        public void bind(Profile profile) {
            this.currentProfile = profile;


        }

        /*
        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            timeTextView = itemView.findViewById(R.id.time);
            locationTextView = itemView.findViewById(R.id.location);
            bioTextView = itemView.findViewById(R.id.description);
            likeButton = itemView.findViewById(R.id.likeButton);
            starImageView = itemView.findViewById(R.id.starImageView);
            verifiedBadge = itemView.findViewById(R.id.verifiedBadge);
            certifiedBadge = itemView.findViewById(R.id.certifiedBadge);
            if (likeButton != null) {
                likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Toggle the highlighted state
                        boolean isHighlighted = !selectedProfiles.contains(filteredProfiles.get(getAdapterPosition()));

                        // Update the image based on the highlighted state
                        likeButton.setImageResource(isHighlighted ? R.drawable.save : R.drawable.unsave);

                        // Notify the listener about the liked state change and pass the profile data
                        if (onLikedStateChangeListener != null) {
                            onLikedStateChangeListener.onLikedStateChanged(filteredProfiles.get(getAdapterPosition()), isHighlighted);
                        }
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // Get the selected profile
                            Profile selectedProfile = filteredProfiles.get(position);

                            // Navigate to ProfileDetailsActivity with the selected profile
                            Intent intent = new Intent(view.getContext(), ProfileDetailsActivity.class);
                            intent.putExtra("profile", selectedProfile);
                            view.getContext().startActivity(intent);
                        }
                    }
                });
            }
        }
        public void bind(Profile profile) {
            this.currentProfile = profile;


        }
        */
    }

    public interface OnLikedStateChangeListener {
        void onLikedStateChanged(Profile profile, boolean isLiked);
    }
}
