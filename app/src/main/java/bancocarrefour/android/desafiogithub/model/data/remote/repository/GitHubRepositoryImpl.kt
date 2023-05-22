package bancocarrefour.android.desafiogithub.model.data.remote.repository

import bancocarrefour.android.desafiogithub.model.data.Constants.API_ERROR
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubUser
import bancocarrefour.android.desafiogithub.model.data.remote.GitHubApi
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubRepo
import bancocarrefour.android.desafiogithub.model.data.remote.response.ResponseState
import bancocarrefour.android.desafiogithub.model.domain.repository.GitHubRepository
import javax.inject.Inject

/**
 * Implementation of the GitHub repository
 */
class GitHubRepositoryImpl @Inject constructor(private val api: GitHubApi) : GitHubRepository {

    override suspend fun getUsers(lastId: Long): ResponseState<List<GitHubUser>?> {

        return try {
            val response = api.getUsers(lastId)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let ResponseState.success(it)
                } ?: ResponseState.error(API_ERROR, null)
            } else {
                ResponseState.error(API_ERROR, null)
            }
        } catch (e: Exception){
            ResponseState.error(API_ERROR, null)
        }
    }

    override suspend fun getUserByName(userName: String): ResponseState<GitHubUser?> {
        return try {
            val response = api.getUserByName(userName)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let ResponseState.success(it)
                } ?: ResponseState.error(API_ERROR, null)
            } else {
                ResponseState.error(API_ERROR, null)
            }
        } catch (e: Exception){
            ResponseState.error(API_ERROR, null)
        }
    }

    override suspend fun getUserRepos(name: String): ResponseState<List<GitHubRepo>?> {
        return try {
            val response = api.getUserRepos(name)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let ResponseState.success(it)
                } ?: ResponseState.error(API_ERROR, null)
            } else {
                ResponseState.error(API_ERROR, null)
            }
        } catch (e: Exception){
            ResponseState.error(API_ERROR, null)
        }
    }
}