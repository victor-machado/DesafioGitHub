package bancocarrefour.android.desafiogithub.model.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Data class for github user
 */
data class GitHubUser(
    val login: String? = "",
    val id: Long = 0,
    @SerializedName("node_id") val nodeId: String? = "",
    @SerializedName("avatar_url") val avatarUrl: String? = "",
    @SerializedName("gravatar_id") val gravatarId: String? = "",
    val url: String? = "",
    @SerializedName("html_url") val htmlUrl: String? = "",
    @SerializedName("followers_url") val followersUrl: String? = "",
    @SerializedName("following_url") val followingUrl: String? = "",
    @SerializedName("gists_url") val gistsUrl: String? = "",
    @SerializedName("starred_url") val starredUrl: String? = "",
    @SerializedName("subscriptions_url") val subscriptionsUrl: String? = "",
    @SerializedName("organizations_url") val organizationsUrl: String? = "",
    @SerializedName("repos_url") val reposUrl: String? = "",
    @SerializedName("events_url") val eventsUrl: String? = "",
    @SerializedName("received_events_url") val receivedEventsUrl: String? = "",
    val type: String? = "",
    @SerializedName("site_admin") val siteAdmin: Boolean = false,
    val name: String? = "",
    val company: String? = "",
    val location: String? = "",
    @SerializedName("public_repos") val publicRepos: Long = 0,
    val followers: Long = 0,
    val following: Long = 0,
    @SerializedName("created_at") val createdAt: String? = "",
    @SerializedName("updated_at") val updatedAt: String? = ""
) {

    /**
     * Function to help on user search
     */
    fun matchSearchQuery(query: String): Boolean {
        val matchingResults = listOf(
            "$login"
        )

        return matchingResults.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
