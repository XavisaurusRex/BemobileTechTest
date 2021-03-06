package cat.devsofthecoast.bemobiletechtest.common.data.remote.error

sealed class AsyncError(val debugMessage: String) {
    class ConnectionError(debugMessage: String) : AsyncError(debugMessage)
    class DataParseError(debugMessage: String) : AsyncError(debugMessage)
    class InvalidTokenError(debugMessage: String) : AsyncError(debugMessage)
    class ServerError(val code: Int, debugMessage: String) : AsyncError(debugMessage)
    class UnknownError(debugMessage: String = "Unknown Error", val errorThrowed: Throwable? = null) : AsyncError(debugMessage)
    open class CustomError(debugMessage: String) : AsyncError(debugMessage)
}