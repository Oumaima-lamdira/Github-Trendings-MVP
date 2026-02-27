package com.example.githubtrendings.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * MODEL dans MVP
 * Represente la reponse de l'API GitHub Search
 * L'API GitHub Search retourne une enveloppe autour des repos
 */
public class RepoResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("items")
    private List<Repo> items;

    public int getTotalCount() { return totalCount; }
    public List<Repo> getItems() { return items; }
}
