package com.example.githubtrendings.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.githubtrendings.R;
import com.example.githubtrendings.databinding.ItemRepoBinding;
import com.example.githubtrendings.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * VIEW dans MVP
 * Adaptateur RecyclerView pour afficher la liste des repos
 */
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {

    private final List<Repo> repos = new ArrayList<>();

    public void setRepos(List<Repo> newRepos) {
        repos.clear();
        repos.addAll(newRepos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRepoBinding binding = ItemRepoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new RepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(repos.get(position));
    }

    @Override
    public int getItemCount() { return repos.size(); }

    static class RepoViewHolder extends RecyclerView.ViewHolder {
        private final ItemRepoBinding binding;

        RepoViewHolder(ItemRepoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Repo repo) {
            binding.tvRepoName.setText(repo.getName());
            binding.tvOwner.setText(repo.getOwner() != null ? repo.getOwner().getLogin() : "");
            binding.tvDescription.setText(
                    repo.getDescription() != null && !repo.getDescription().isEmpty()
                            ? repo.getDescription() : "Aucune description");
            binding.tvStars.setText(formatCount(repo.getStars()) + " ★");
            binding.tvForks.setText(formatCount(repo.getForks()) + " 🍴");
            binding.tvLanguage.setText(
                    repo.getLanguage() != null ? repo.getLanguage() : "N/A");

            if (repo.getOwner() != null) {
                Glide.with(binding.getRoot().getContext())
                        .load(repo.getOwner().getAvatarUrl())
                        .placeholder(R.drawable.ic_github_placeholder)
                        .circleCrop()
                        .into(binding.ivAvatar);
            }
        }

        private String formatCount(int count) {
            if (count >= 1000) {
                return String.format("%.1fk", count / 1000.0);
            }
            return String.valueOf(count);
        }
    }
}
