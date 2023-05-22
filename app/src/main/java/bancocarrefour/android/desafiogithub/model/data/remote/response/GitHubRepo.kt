package bancocarrefour.android.desafiogithub.model.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Data class for github user repository
 */
data class GitHubRepo(
    val id: Long = 0,
    val name: String? = "",
    val private: Boolean = false,
    @SerializedName("html_url") val htmlUrl: String? = "",
    val description: String? = "",
    @SerializedName("created_at") val createdAt: String? = "",
    @SerializedName("updated_at") val updatedAt: String? = "",
    @SerializedName("pushed_at") val pushedAt: String? = "",
    val language: String? = "",
    @SerializedName("has_downloads") val hasDownloads: Boolean = false,
    val disabled: Boolean = false,
    val visibility: String? = "",
    @SerializedName("default_branch") val defaultBranch: String? = ""
)