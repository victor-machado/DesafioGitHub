package bancocarrefour.android.desafiogithub

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import bancocarrefour.android.desafiogithub.ui.DetailScreen
import bancocarrefour.android.desafiogithub.ui.GitHubUserViewModel
import bancocarrefour.android.desafiogithub.ui.SearchListScreen
import bancocarrefour.android.desafiogithub.ui.theme.Purple40
import bancocarrefour.android.desafiogithub.ui.theme.Purple80
import bancocarrefour.android.desafiogithub.ui.theme.PurpleGrey40

/**
 * Enum of app screens.
 */
enum class GitHubUserScreen {
    SearchList,
    Detail
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun GitHubUserAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentScreen: String
) {
    TopAppBar(
        title = { Text(currentScreen) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun AppScreen(
    viewModel: GitHubUserViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    viewModel.fetchUsers()

    Scaffold(
        topBar = {
            GitHubUserAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = getTitle(
                    backStackEntry?.destination?.route,
                    context = LocalContext.current
                ),
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = GitHubUserScreen.SearchList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = GitHubUserScreen.SearchList.name) {
                SearchListScreen(
                    viewModel = viewModel,
                    onUserSelected = {
                        viewModel.getUserDetails(it.login.toString())
                        navController.navigate(GitHubUserScreen.Detail.name)
                    }
                )
            }
            composable(route = GitHubUserScreen.Detail.name) {
                DetailScreen(
                    viewModel = viewModel
                )
            }
        }

        CircularProgress(show = uiState.isLoading)
        ErrorDialog(openDialog = uiState.error)
    }
}

/**
 * Generic dialog to show errors.
 */
@Composable
fun ErrorDialog(openDialog: MutableState<Boolean>) {
    if(openDialog.value){
        Dialog(
            onDismissRequest = { openDialog.value = false}
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = "Ocorreu um erro, por favor tente novamente em instantes.",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Black
                    )
                    TextButton(
                        onClick = {openDialog.value = false},
                        colors = ButtonDefaults.textButtonColors()
                    ) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}

/**
 * Function to show loading.
 */
@Composable
fun CircularProgress(show: MutableState<Boolean>) {
    if(show.value){
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

/**
 * Function to change title bar text.
 */
private fun getTitle(rout: String?, context: Context): String {
    var title = context.getString(R.string.app_name)
    rout.let {
        if (rout != GitHubUserScreen.SearchList.name) {
            title = rout.toString()
        }
    }
    return title
}
