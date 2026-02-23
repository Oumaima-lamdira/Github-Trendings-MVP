package com.example.githubtrendings.model;

import javax.inject.Inject;
import retrofit2.Callback;

/**
 * MODEL dans MVP
 * Repository : gere l'acces aux donnees (API GitHub)
 */
public class RepoRepository {

    private final GithubApi api;

    @Inject
    public RepoRepository(GithubApi api) {
        this.api = api;
    }

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
