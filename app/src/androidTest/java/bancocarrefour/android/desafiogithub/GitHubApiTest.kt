package bancocarrefour.android.desafiogithub

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class GitHubApiTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup(){
        rule.activity.setContent { AppScreen() }
        rule.waitForIdle()
        waitFor(5)
    }

    @Test
    fun load_listItems() {
        rule.onNodeWithText("GitHub Users").assertIsDisplayed()
        rule.onNodeWithText("mojombo").assertIsDisplayed()
        rule.onNodeWithText("defunkt").assertIsDisplayed()
        rule.onNodeWithText("wycats").assertIsDisplayed()
        rule.onNodeWithText("ezmobius").assertIsDisplayed()
    }

    @Test
    fun open_detailScreen() {
        rule.onNodeWithText("mojombo").performClick()
        rule.onNodeWithText("Detail").assertIsDisplayed()
    }

    @Test
    fun search_username() {
        rule.onNodeWithText("Buscar por nome de usuário").assertIsDisplayed()
        rule.onNodeWithText("Buscar por nome de usuário").performTextInput("vanpelt")
        rule.waitForIdle()
        waitFor(5)
        rule.onAllNodesWithText("vanpelt")[0].assertIsDisplayed()
    }

    private fun waitFor(time: Long) {
        Thread.sleep(time * 1000)
    }

}