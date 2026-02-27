package com.example.githubtrendings;

import android.app.Application;

import com.example.githubtrendings.di.AppComponent;
import com.example.githubtrendings.di.DaggerAppComponent;
import com.example.githubtrendings.di.NetworkModule;
import com.example.githubtrendings.di.RepositoryModule;

/**
 * Classe Application
 * - Initialise Dagger 2 au démarrage
 */
public class GithubApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // ── Dagger 2 ──────────────────────────────────────────────────────────
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
