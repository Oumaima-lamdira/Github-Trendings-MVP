package com.example.githubtrendings.di;

import com.example.githubtrendings.model.GithubApi;
import com.example.githubtrendings.model.RepoRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private NetworkModule networkModule;

    private RepositoryModule repositoryModule;

    private Builder() {
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public AppComponent build() {
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      return new AppComponentImpl(networkModule, repositoryModule);
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final AppComponentImpl appComponentImpl = this;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<GithubApi> provideGithubApiProvider;

    private Provider<RepoRepository> provideRepoRepositoryProvider;

    private AppComponentImpl(NetworkModule networkModuleParam,
        RepositoryModule repositoryModuleParam) {

      initialize(networkModuleParam, repositoryModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final NetworkModule networkModuleParam,
        final RepositoryModule repositoryModuleParam) {
      this.provideOkHttpClientProvider = DoubleCheck.provider(NetworkModule_ProvideOkHttpClientFactory.create(networkModuleParam));
      this.provideRetrofitProvider = DoubleCheck.provider(NetworkModule_ProvideRetrofitFactory.create(networkModuleParam, provideOkHttpClientProvider));
      this.provideGithubApiProvider = DoubleCheck.provider(NetworkModule_ProvideGithubApiFactory.create(networkModuleParam, provideRetrofitProvider));
      this.provideRepoRepositoryProvider = DoubleCheck.provider(RepositoryModule_ProvideRepoRepositoryFactory.create(repositoryModuleParam, provideGithubApiProvider));
    }

    @Override
    public RepoRepository getRepoRepository() {
      return provideRepoRepositoryProvider.get();
    }
  }
}
