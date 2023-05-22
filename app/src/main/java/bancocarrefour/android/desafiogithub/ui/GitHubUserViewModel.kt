package bancocarrefour.android.desafiogithub.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubUser
import bancocarrefour.android.desafiogithub.model.data.remote.response.Status
import bancocarrefour.android.desafiogithub.model.domain.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel of users screen.
 */
@HiltViewModel
class GitHubUserViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    /**
     * State of users screen
     */
    private val _uiState = MutableStateFlow(GitHubUserUiState())
    val uiState: StateFlow<GitHubUserUiState> = _uiState.asStateFlow()

    /**
     * State for users search
     */
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    private val _lastUserId = MutableStateFlow(0L)

    /**
     * Filter and search api by username
     */
    private val _filteredUsers = MutableStateFlow(listOf<GitHubUser>())
    val filteredUsers = searchText
        .debounce(1500L)  // wait for user stop typing
        .onEach { _isSearching.update { true } }
        .combine(_filteredUsers) { text, users ->
            if (text.isBlank()) {
                users
            } else {
                getUserByUsername(text)
                users.filter {
                    it.matchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _filteredUsers.value
        )

    /**
     * On user search text changed
     */
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    /**
     * Get users from api
     */
    private suspend fun getUsers() {
        _uiState.value.isLoading.value = true
        val users = repository.getUsers(_lastUserId.value)
        if (users.status == Status.SUCCESS) {
            _uiState.value.userList.value += users.data!!
            _lastUserId.value = _uiState.value.userList.value.last().id
            _filteredUsers.value = _uiState.value.userList.value
        } else {
            _uiState.value.error.value = true
        }
        _uiState.value.isLoading.value = false
    }

    /**
     * Get user by username
     */
    private suspend fun getUserByUsername(userName: String) {
        _uiState.value.isLoading.value = true
        val user = repository.getUserByName(userName)
        if (user.status == Status.SUCCESS) {
            val search = _uiState.value.userList.value.toMutableList()
            user.data?.let {
                if (!search.contains(it)) {
                    search.add(0, it)
                    _uiState.value.userList.value = search
                    _filteredUsers.value = search
                }
            }
        }
        _uiState.value.isLoading.value = false
    }

    /**
     * Get users function
     */
    fun fetchUsers() {
        viewModelScope.launch {
            getUsers()
        }
    }

    /**
     * Get user details from api
     */
    fun getUserDetails(login: String) {
        _uiState.value.isLoading.value = true
        viewModelScope.launch {

            coroutineScope {

                val userReq = async { repository.getUserByName(login) }
                val reposReq = async { repository.getUserRepos(login) }

                val user = userReq.await()
                val repos = reposReq.await()
                if (user.status == Status.SUCCESS && repos.status == Status.SUCCESS) {
                    _uiState.value.selected.value = user.data!!
                    _uiState.value.userRepoList.value = repos.data!!
                } else {
                    _uiState.value.error.value = true
                }
            }

        }
        _uiState.value.isLoading.value = false
    }

}