package com.example.githubtrendings.di;

import com.example.githubtrendings.model.RepoRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * COMPOSANT DAGGER 2
 * Point d'entree du graphe de dependances
 */
@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface AppComponent {
    RepoRepository getRepoRepository();
}
