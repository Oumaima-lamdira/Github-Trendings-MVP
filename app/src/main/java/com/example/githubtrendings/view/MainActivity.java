package com.example.githubtrendings.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubtrendings.GithubApp;
import com.example.githubtrendings.R;
import com.example.githubtrendings.databinding.ActivityMainBinding;
import com.example.githubtrendings.model.Repo;
import com.example.githubtrendings.model.RepoRepository;
import com.example.githubtrendings.presenter.ReposContract;
import com.example.githubtrendings.presenter.ReposPresenter;

import java.util.List;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
/**
 * VIEW dans MVP
 * - Implemente ReposContract.View
 * - Affiche les donnees recues du Presenter
 * - Delegue TOUTE logique metier au Presenter
 */
public class MainActivity extends AppCompatActivity implements ReposContract.View {

    private ActivityMainBinding binding;
    private ReposAdapter adapter;
    private ReposPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        initPresenter();
        setupRecyclerView();
        setupSwipeRefresh();

        presenter.loadTrendingRepos();
    }

    private void initPresenter() {
        RepoRepository repository = ((GithubApp) getApplication())
                .getAppComponent().getRepoRepository();/**obtient via Dagger*/
        presenter = new ReposPresenter(repository);
        presenter.attachView(this);
    }

    private void setupRecyclerView() {
        adapter = new ReposAdapter(repo -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_REPO_NAME,     repo.getName());
            intent.putExtra(DetailActivity.EXTRA_REPO_FULLNAME, repo.getFullName());
            intent.putExtra(DetailActivity.EXTRA_REPO_DESC,     repo.getDescription());
            intent.putExtra(DetailActivity.EXTRA_REPO_STARS,    repo.getStars());
            intent.putExtra(DetailActivity.EXTRA_REPO_FORKS,    repo.getForks());
            intent.putExtra(DetailActivity.EXTRA_REPO_LANGUAGE, repo.getLanguage());
            intent.putExtra(DetailActivity.EXTRA_REPO_URL,      repo.getHtmlUrl());
            if (repo.getOwner() != null) {
                intent.putExtra(DetailActivity.EXTRA_REPO_OWNER,  repo.getOwner().getLogin());
                intent.putExtra(DetailActivity.EXTRA_REPO_AVATAR, repo.getOwner().getAvatarUrl());
            }
            startActivity(intent);
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        // Pagination au scroll
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                if (!rv.canScrollVertically(1) && dy > 0) {
                    presenter.loadNextPage();
                }
            }
        });
    }

    private void setupSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light
        );
        binding.swipeRefresh.setOnRefreshListener(() -> presenter.refresh());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // ─── Implementation de ReposContract.View ────────────────────

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);
        binding.tvEmpty.setVisibility(View.GONE);
        binding.tvError.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.swipeRefresh.setRefreshing(false);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRepos(List<Repo> repos) {
        binding.tvEmpty.setVisibility(View.GONE);
        binding.tvError.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
        adapter.setRepos(repos);
    }

    @Override
    public void showError(String message) {
        binding.progressBar.setVisibility(View.GONE);
        binding.swipeRefresh.setRefreshing(false);
        if (adapter.getItemCount() == 0) {
            binding.recyclerView.setVisibility(View.GONE);
            binding.tvError.setVisibility(View.VISIBLE);
            binding.tvError.setText(message);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showEmptyState() {
        binding.recyclerView.setVisibility(View.GONE);
        binding.tvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingMore() {
        binding.progressBarBottom.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingMore() {
        binding.progressBarBottom.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
