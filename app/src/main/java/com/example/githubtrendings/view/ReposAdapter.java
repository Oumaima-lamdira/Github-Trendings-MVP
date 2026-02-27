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
import java.util.Locale;
/**Le pont entre la liste de `Repo` et le `RecyclerView`*/
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {

    public interface OnRepoClickListener {
        void onRepoClick(Repo repo);
    }

    private final OnRepoClickListener listener;
    private final List<Repo> repos = new ArrayList<>();

    public ReposAdapter(OnRepoClickListener listener) {
        this.listener = listener;
    }

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
        return new RepoViewHolder(binding, listener); // ← passe listener ici
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(repos.get(position));
    }

    @Override
    public int getItemCount() { return repos.size(); }

    // ← RepoViewHolder reçoit le listener en paramètre
    static class RepoViewHolder extends RecyclerView.ViewHolder {
        private final ItemRepoBinding binding;
        private final OnRepoClickListener listener;

        RepoViewHolder(ItemRepoBinding binding, OnRepoClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        void bind(Repo repo) {
            binding.tvRepoName.setText(repo.getName());
            binding.tvOwner.setText(repo.getOwner() != null ? repo.getOwner().getLogin() : "");
            binding.tvDescription.setText(
                    repo.getDescription() != null && !repo.getDescription().isEmpty()
                            ? repo.getDescription() : "Aucune description");
            binding.tvStars.setText(String.format(Locale.getDefault(), "%s ★", formatCount(repo.getStars())));
            binding.tvForks.setText(String.format(Locale.getDefault(), "%s 🍴", formatCount(repo.getForks())));
            binding.tvLanguage.setText(repo.getLanguage() != null ? repo.getLanguage() : "N/A");

            if (repo.getOwner() != null) {
                Glide.with(binding.getRoot().getContext())
                        .load(repo.getOwner().getAvatarUrl())
                        .placeholder(R.drawable.ic_github_placeholder)
                        .circleCrop()
                        .into(binding.ivAvatar);
            }

            binding.getRoot().setOnClickListener(v -> listener.onRepoClick(repo));
        }

        private String formatCount(int count) {
            if (count >= 1000) {
                return String.format(Locale.getDefault(), "%.1fk", count / 1000.0);
            }
            return String.valueOf(count);
        }
    }
}