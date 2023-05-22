package bancocarrefour.android.desafiogithub.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Text field for user search.
 */
@Composable
fun AppSearchBar(
    viewModel: GitHubUserViewModel
) {
    val searchText by viewModel.searchText.collectAsState()

    TextField(
        value = searchText,
        onValueChange = viewModel::onSearchTextChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text( text = "Buscar por nome de usuário")}
    )
    Spacer(modifier = Modifier.height(16.dp))
}