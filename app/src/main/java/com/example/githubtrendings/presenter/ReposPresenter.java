package com.example.githubtrendings.presenter;

import com.example.githubtrendings.model.Repo;
import com.example.githubtrendings.model.RepoRepository;
import com.example.githubtrendings.model.RepoResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * PRESENTER dans MVP
 * - Contient TOUTE la logique metier
 * - Ne connait PAS Android (pas de Context)
 * - Communique avec la View via ReposContract.View
 * - Communique avec le Model via RepoRepository
 */
public class ReposPresenter implements ReposContract.Presenter {

    private ReposContract.View view;
    private final RepoRepository repository;

    private int currentPage = 1;
    private boolean isLoading = false;
    private final List<Repo> allRepos = new ArrayList<>();

    @Inject
    public ReposPresenter(RepoRepository repository) {
        this.repository = repository;
    }
/**`attachView` / `onDestroy`** → cycle de vie du Presenter lié à l'Activity :
 Activity.onCreate()  → presenter.attachView(this)  → view = this*/
    public void attachView(ReposContract.View view) {
        this.view = view;
    }

    @Override
    public void loadTrendingRepos() {
        if (view == null) return;
        currentPage = 1;
        allRepos.clear();
        view.showLoading();
        fetchRepos(1);
    }

    @Override
    public void loadNextPage() {
        if (isLoading) return;
        fetchRepos(currentPage + 1);
    }

    @Override
    public void refresh() {
        currentPage = 1;
        allRepos.clear();
        if (view != null) view.showLoading();
        fetchRepos(1);
    }
/**Activity.onDestroy() → presenter.onDestroy()       → view = null*/
    @Override
    public void onDestroy() {
        view = null; // Evite les memory leaks
    }

    // ─── Methodes privees ─────────────────────────────────────────

    private void fetchRepos(final int page) {
        if (view == null) return;
        isLoading = true;
        if (page > 1) view.showLoadingMore();

        repository.getTrendingRepos(page, new Callback<RepoResponse>() {
            @Override
            public void onResponse(Call<RepoResponse> call, Response<RepoResponse> response) {
                isLoading = false;
                if (view == null) return;/**guard clause. Si l'Activity est détruite pendant un appel réseau, on ne fait rien pour éviter un crash.*/
                view.hideLoading();
                view.hideLoadingMore();

                if (response.isSuccessful() && response.body() != null) {
                    currentPage = page;
                    List<Repo> newRepos = response.body().getItems();
                    if (newRepos != null) {
                        allRepos.addAll(newRepos);
                    }
                    if (allRepos.isEmpty()) {
                        view.showEmptyState();
                    } else {
                        view.showRepos(new ArrayList<>(allRepos));/**→ on passe une copie à la View pour éviter les modifications concurrentes.*/
                    }
                } else {
                    view.showError("Erreur serveur : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RepoResponse> call, Throwable t) {
                isLoading = false;
                if (view == null) return;
                view.hideLoading();
                view.hideLoadingMore();
                view.showError("Erreur réseau : " + t.getMessage());
            }
        });
    }
}
