package com.example.githubtrendings.di;

import com.example.githubtrendings.model.GithubApi;
import com.example.githubtrendings.model.RepoRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * MODULE DAGGER 2
 * Fournit le Repository
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public RepoRepository provideRepoRepository(GithubApi api) {
        return new RepoRepository(api);
    }
}
