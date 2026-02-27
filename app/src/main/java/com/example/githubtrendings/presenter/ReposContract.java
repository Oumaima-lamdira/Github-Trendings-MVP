package com.example.githubtrendings.presenter;

import com.example.githubtrendings.model.Repo;
import java.util.List;

/**
 * CONTRACT MVP
 * Definit le contrat entre View et Presenter
 * Pourquoi 2 interfaces dans 1 fichier ? Pour regrouper tout ce qui concerne l'écran "liste des repos" au même endroit
 */
public interface ReposContract {

    /**
     * Ce que la VIEW peut faire (afficher des choses)
     * ce que MainActivity peut afficher. MainActivity est obligée d'implémenter toutes ces méthodes.
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
     * → ce que ReposPresenter peut faire. MainActivity appelle ces méthodes en réponse aux actions utilisateur.
     */
    interface Presenter {
        void loadTrendingRepos();
        void loadNextPage();
        void refresh();
        void onDestroy();
    }
}
