package com.example.githubtrendings.di;

import com.example.githubtrendings.model.GithubApi;
import com.example.githubtrendings.model.RepoRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RepositoryModule_ProvideRepoRepositoryFactory implements Factory<RepoRepository> {
  private final RepositoryModule module;

  private final Provider<GithubApi> apiProvider;

  public RepositoryModule_ProvideRepoRepositoryFactory(RepositoryModule module,
      Provider<GithubApi> apiProvider) {
    this.module = module;
    this.apiProvider = apiProvider;
  }

  @Override
  public RepoRepository get() {
    return provideRepoRepository(module, apiProvider.get());
  }

  public static RepositoryModule_ProvideRepoRepositoryFactory create(RepositoryModule module,
      Provider<GithubApi> apiProvider) {
    return new RepositoryModule_ProvideRepoRepositoryFactory(module, apiProvider);
  }

  public static RepoRepository provideRepoRepository(RepositoryModule instance, GithubApi api) {
    return Preconditions.checkNotNullFromProvides(instance.provideRepoRepository(api));
  }
}
