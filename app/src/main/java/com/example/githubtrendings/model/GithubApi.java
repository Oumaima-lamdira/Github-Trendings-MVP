package com.example.githubtrendings.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * MODEL dans MVP
 * Interface Retrofit pour l'API GitHub Search
 * Utilise des Callbacks (pas de Coroutines - architecture Legacy)
 * C'est le menu de l'API — il définit les requêtes HTTP disponibles.
 */
public interface  GithubApi {
    /** @GET("search/repositories")`** → fait une requête GET vers `https://api.github.com/search/repositories`*/
    @GET("search/repositories")
    Call<RepoResponse> searchRepositories(
            @Query("q") String query,
            @Query("sort") String sort,
            @Query("order") String order,
            @Query("per_page") int perPage,
            @Query("page") int page
    );

    /**
     * Calcule dynamiquement la date d'il y a 30 jours
     * pour construire la requete GitHub
     */
    static String buildQuery() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return "created:>" + sdf.format(calendar.getTime());
    }
}
