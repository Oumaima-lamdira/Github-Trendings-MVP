package com.example.githubtrendings.di;

import com.example.githubtrendings.model.RepoRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * COMPOSANT DAGGER 2
 * Point d'entree du graphe de dependances
 * C'est quoi ? C'est le chef d'orchestre de Dagger 2. Il assemble toutes les dépendances.
 * Ligne par ligne :
 * @Singleton → une seule instance de chaque objet dans toute l'app
 * @Component(modules = {...}) → dit à Dagger "utilise ces 2 modules pour construire les objets"
 * getRepoRepository() → expose le Repository pour que MainActivity puisse l'obtenir
 */
@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface AppComponent {
    RepoRepository getRepoRepository();
}
