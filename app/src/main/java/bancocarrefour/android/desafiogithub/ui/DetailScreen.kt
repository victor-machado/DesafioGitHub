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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import bancocarrefour.android.desafiogithub.R
import coil.compose.rememberImagePainter
import coil.size.OriginalSize

/**
 * User detail screen.
 */
@Composable
fun DetailScreen(
    viewModel: GitHubUserViewModel
) {
    val state = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .weight(1f, fill = false)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = state.value.selected.value.avatarUrl,
                        builder = {
                            size(OriginalSize)
                        },
                    ),
                    contentDescription = null
                )
            }
            Text(
                text = state.value.selected.value.name ?: "",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.login ?: "",
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.url),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.htmlUrl ?: "",
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.user_type),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.type ?: "",
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.admin),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.siteAdmin.toString(),
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.company),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.company ?: "",
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.location),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.location ?: "",
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.followers),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.followers.toString(),
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.following),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.following.toString(),
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.created),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.createdAt ?: "",
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.updated),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = state.value.selected.value.updatedAt ?: "",
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
        }

        Row(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.repos),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Row {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(items = state.value.userRepoList.value.toList()) { item ->
                    Row(
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                    ) {
                        Text(text = item.name.toString())
                    }
                }
            }
        }
    }
}