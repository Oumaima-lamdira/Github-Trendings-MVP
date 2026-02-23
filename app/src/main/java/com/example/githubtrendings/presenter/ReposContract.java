package com.example.githubtrendings.presenter;

import com.example.githubtrendings.model.Repo;
import java.util.List;

/**
 * CONTRACT MVP
 * Definit le contrat entre View et Presenter
 */
public interface ReposContract {

    /**
     * Ce que la VIEW peut faire (afficher des choses)
     */
    interface View {
        void showLoading();
        void hideLoading();
        void showRepos(List<Repo> repos);
        void showError(String message);
        void showEmptyState();
        void showLoadingMore();
        void hideLoadingMore();
    }

    /**
     * Ce que le PRESENTER peut faire (logique metier)
     */
    interface Presenter {
        void loadTrendingRepos();
        void loadNextPage();
        void refresh();
        void onDestroy();
    }
}
