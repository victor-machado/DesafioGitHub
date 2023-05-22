package bancocarrefour.android.desafiogithub.model.di.repository

import bancocarrefour.android.desafiogithub.model.data.remote.repository.GitHubRepositoryImpl
import bancocarrefour.android.desafiogithub.model.domain.repository.GitHubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for providing the repositories
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGitHubRepository(
        gitHubRepositoryImpl: GitHubRepositoryImpl
    ): GitHubRepository
}