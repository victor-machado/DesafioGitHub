package bancocarrefour.android.desafiogithub.model.domain.repository

import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubRepo
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubUser
import bancocarrefour.android.desafiogithub.model.data.remote.response.ResponseState

/**
 * Interface for GitHub repository
 */
interface GitHubRepository {

    /**
     * Get list of GiHub users
     */
    suspend fun getUsers(lastId: Long): ResponseState<List<GitHubUser>?>

    /**
     * Get users by name
     */
    suspend fun getUserByName(name: String): ResponseState<GitHubUser?>

    /**
     * Get the list of user repositories
     */
    suspend fun getUserRepos(name: String): ResponseState<List<GitHubRepo>?>
}