package bancocarrefour.android.desafiogithub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bancocarrefour.android.desafiogithub.model.data.remote.response.GitHubUser
import coil.compose.rememberImagePainter
import coil.size.OriginalSize

/**
 * Search list screen.
 */
@Composable
fun SearchListScreen(
    viewModel: GitHubUserViewModel,
    onUserSelected: (GitHubUser) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        AppSearchBar(viewModel)
        UserList(viewModel, onUserSelected)
    }
}

/**
 * The user list.
 */
@Composable
fun UserList(
    viewModel: GitHubUserViewModel,
    onUserSelected: (GitHubUser) -> Unit,
) {
    val users = viewModel.filteredUsers.collectAsState()
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {
        items(items = users.value.toList()) { item ->
            UserCard(item, onUserSelected)
        }
    }

    listState.OnBottomReached {
        viewModel.fetchUsers()
    }
}

/**
 * Card to show users in the list.
 */
@Composable
fun UserCard(
    user: GitHubUser,
    onUserSelected: (GitHubUser) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(70.dp)
                .selectable(
                    enabled = true,
                    selected = false,
                    onClick = { onUserSelected(user) }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .weight(1f, fill = false)
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = user.avatarUrl,
                        builder = {
                            size(OriginalSize)
                        },
                    ),
                    contentDescription = null
                )
            }
            Text(
                text = user.login.toString(),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
            )
        }

    }
    Spacer(modifier = Modifier.height(16.dp))
}