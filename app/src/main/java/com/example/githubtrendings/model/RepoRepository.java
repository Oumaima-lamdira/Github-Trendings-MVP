package com.example.githubtrendings.model;

import javax.inject.Inject;
import retrofit2.Callback;

/**
 * MODEL dans MVP
 * Repository : gere l'acces aux donnees (API GitHub)
 * C'est le seul point d'accès aux données. Le Presenter ne parle jamais directement à l'API — il passe toujours par le Repository.
 */
public class RepoRepository {

    private final GithubApi api;
/** @Inject → Dagger injecte automatiquement GithubApi dans le constructeur.*/
    @Inject
    public RepoRepository(GithubApi api) {
        this.api = api;
    }
/**.enqueue(callback) → exécute la requête de façon asynchrone (pas de blocage du thread principal). La réponse arrive dans onResponse() ou onFailure().*/
    public void getTrendingRepos(int page, Callback<RepoResponse> callback) {
        api.searchRepositories(
                GithubApi.buildQuery(),
                "stars",
                "desc",
                20,
                page
        ).enqueue(callback);
    }
}
