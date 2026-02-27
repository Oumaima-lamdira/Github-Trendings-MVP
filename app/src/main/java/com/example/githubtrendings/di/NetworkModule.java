package com.example.githubtrendings.di;

import com.example.githubtrendings.model.GithubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MODULE DAGGER 2
 * Fournit les dependances reseau
 * Il fabrique tous les objets réseau dans l'ordre correct
 */
@Module
public class NetworkModule {
    /**
     OkHttpClient : le client HTTP qui fait les vraies requêtes réseau + logs
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
    /**
     Retrofit : prend l'OkHttpClient et sait convertir JSON en Java grâce à Gson
     */
    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /**
     GithubApi : l'interface Retrofit devient un objet utilisable
     */
    @Provides
    @Singleton
    public GithubApi provideGithubApi(Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }
}
