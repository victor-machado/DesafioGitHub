package bancocarrefour.android.desafiogithub.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubRepo
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubUser

/**
 * Data class that represents the current UI state
 */
data class GitHubUserUiState(
    val selected: MutableState<GitHubUser> = mutableStateOf(GitHubUser()),
    val userList: MutableState<List<GitHubUser>> = mutableStateOf(mutableListOf()),
    val userRepoList: MutableState<List<GitHubRepo>> = mutableStateOf(mutableListOf()),
    val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val error: MutableState<Boolean> = mutableStateOf(false)

)
