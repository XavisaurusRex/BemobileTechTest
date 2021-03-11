package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.error

import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.AsyncError
import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.KoreException
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.logging.Level
import java.util.logging.Logger

object RemoteErrorManagement {

    private val log = Logger.getLogger(RemoteErrorManagement::class.java.name)

    /**
     * Calls the specified function [block] and returns its result. Catch any Exception and convert to a SdosException.
     */
    inline fun <T> wrap(block: () -> T): T {
        return try {
            block()
        } catch (e: Throwable) {
            throw KoreException(processError(e))
        }
    }

    fun processError(throwable: Throwable): AsyncError {
        log.log(Level.INFO, "RemoteErrorManagement", throwable)
        return when (throwable) {
            is HttpException -> processRetrofitError(throwable)
            is UnknownHostException -> AsyncError.ConnectionError(throwable.message ?: "Connection error")
            is JsonParseException -> AsyncError.DataParseError(throwable.message ?: "Parsing response error")
            else -> AsyncError.UnknownError(throwable.message ?: "Unknown error", throwable)
        }
    }

    private fun processRetrofitError(httpException: HttpException): AsyncError {
        val errorCode = httpException.code()
        val url = httpException.response()?.raw()?.request?.url?.toString() ?: ""
        return AsyncError.ServerError(errorCode, url)
    }
}