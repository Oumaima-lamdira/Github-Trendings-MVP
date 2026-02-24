package com.example.githubtrendings.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.githubtrendings.R;
import com.example.githubtrendings.databinding.ActivityDetailBinding;
import com.example.githubtrendings.model.Repo;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_REPO_NAME     = "extra_repo_name";
    public static final String EXTRA_REPO_FULLNAME = "extra_repo_fullname";
    public static final String EXTRA_REPO_DESC     = "extra_repo_desc";
    public static final String EXTRA_REPO_STARS    = "extra_repo_stars";
    public static final String EXTRA_REPO_FORKS    = "extra_repo_forks";
    public static final String EXTRA_REPO_LANGUAGE = "extra_repo_language";
    public static final String EXTRA_REPO_AVATAR   = "extra_repo_avatar";
    public static final String EXTRA_REPO_OWNER    = "extra_repo_owner";
    public static final String EXTRA_REPO_URL      = "extra_repo_url";

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loadData();
    }

    private void loadData() {
        String name     = getIntent().getStringExtra(EXTRA_REPO_NAME);
        String fullName = getIntent().getStringExtra(EXTRA_REPO_FULLNAME);
        String desc     = getIntent().getStringExtra(EXTRA_REPO_DESC);
        int stars       = getIntent().getIntExtra(EXTRA_REPO_STARS, 0);
        int forks       = getIntent().getIntExtra(EXTRA_REPO_FORKS, 0);
        String language = getIntent().getStringExtra(EXTRA_REPO_LANGUAGE);
        String avatar   = getIntent().getStringExtra(EXTRA_REPO_AVATAR);
        String owner    = getIntent().getStringExtra(EXTRA_REPO_OWNER);
        String url      = getIntent().getStringExtra(EXTRA_REPO_URL);

        binding.collapsingToolbar.setTitle(name);
        binding.tvFullName.setText(fullName);
        binding.tvOwner.setText("👤 " + (owner != null ? owner : ""));
        binding.tvDescription.setText(desc != null && !desc.isEmpty() ? desc : "Aucune description");
        binding.tvStars.setText(formatCount(stars) + " ⭐ Stars");
        binding.tvForks.setText(formatCount(forks) + " 🍴 Forks");
        binding.tvLanguage.setText("💻 " + (language != null ? language : "N/A"));
        binding.tvUrl.setText(url);

        Glide.with(this)
                .load(avatar)
                .placeholder(R.drawable.ic_github_placeholder)
                .circleCrop()
                .into(binding.ivAvatar);
    }

    private String formatCount(int count) {
        if (count >= 1000) return String.format("%.1fk", count / 1000.0);
        return String.valueOf(count);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}