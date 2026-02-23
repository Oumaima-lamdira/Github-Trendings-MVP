package com.example.githubtrendings.model;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RepoRepository_Factory implements Factory<RepoRepository> {
  private final Provider<GithubApi> apiProvider;

  public RepoRepository_Factory(Provider<GithubApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public RepoRepository get() {
    return newInstance(apiProvider.get());
  }

  public static RepoRepository_Factory create(Provider<GithubApi> apiProvider) {
    return new RepoRepository_Factory(apiProvider);
  }

  public static RepoRepository newInstance(GithubApi api) {
    return new RepoRepository(api);
  }
}
