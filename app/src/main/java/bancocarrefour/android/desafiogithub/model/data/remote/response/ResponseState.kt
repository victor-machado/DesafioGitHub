package bancocarrefour.android.desafiogithub.model.data.remote.response

/**
 * Data class for handling api responses
 */
data class ResponseState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResponseState<T> {
            return ResponseState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResponseState<T> {
            return ResponseState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResponseState<T> {
            return ResponseState(Status.LOADING, data, null)
        }
    }
}

/**
 * Enum for response status
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}