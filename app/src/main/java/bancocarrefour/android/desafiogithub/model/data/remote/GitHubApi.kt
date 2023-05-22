package bancocarrefour.android.desafiogithub.model.data.remote

import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubRepo
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface to GitHub Api
 */
interface GitHubApi {

    /**
     * Function to get the list of users
     */
    @GET("users")
    suspend fun getUsers(
        @Query("since") lastId: Long
    ): Response<List<GitHubUser>>

    /**
     * Function to get user by username
     */
    @GET("users/{Username}")
    suspend fun getUserByName(
        @Path("Username") name: String
    ): Response<GitHubUser>

    /**
     * Function to get user repositories by username
     */
    @GET("users/{Name}/repos")
    suspend fun getUserRepos(
        @Path("Name") name: String,
    ): Response<List<GitHubRepo>>

}