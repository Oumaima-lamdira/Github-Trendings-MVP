package com.example.githubtrendings.model;

import com.google.gson.annotations.SerializedName;

/**
 * MODEL dans MVP
 * Represente un repository GitHub retourne par l'API
 */
public class Repo {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("stargazers_count")
    private int stars;

    @SerializedName("forks_count")
    private int forks;

    @SerializedName("language")
    private String language;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("owner")
    private Owner owner;

    public long getId() { return id; }
    public String getName() { return name; }
    public String getFullName() { return fullName; }
    public String getDescription() { return description; }
    public int getStars() { return stars; }
    public int getForks() { return forks; }
    public String getLanguage() { return language; }
    public String getHtmlUrl() { return htmlUrl; }
    public Owner getOwner() { return owner; }

    public static class Owner {
        @SerializedName("login")
        private String login;

        @SerializedName("avatar_url")
        private String avatarUrl;

        public String getLogin() { return login; }
        public String getAvatarUrl() { return avatarUrl; }
    }
}
