package com.example.githubtrendings.presenter;

import com.example.githubtrendings.model.RepoRepository;
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
public final class ReposPresenter_Factory implements Factory<ReposPresenter> {
  private final Provider<RepoRepository> repositoryProvider;

  public ReposPresenter_Factory(Provider<RepoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ReposPresenter get() {
    return newInstance(repositoryProvider.get());
  }

  public static ReposPresenter_Factory create(Provider<RepoRepository> repositoryProvider) {
    return new ReposPresenter_Factory(repositoryProvider);
  }

  public static ReposPresenter newInstance(RepoRepository repository) {
    return new ReposPresenter(repository);
  }
}
