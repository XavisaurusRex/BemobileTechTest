package cat.devsofthecoast.bemobiletechtest.common.data.remote

import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.AsyncError
import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.KoreException
import cat.devsofthecoast.bemobiletechtest.common.extensions.allscopes.logDebug
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.util.logging.Level
import java.util.logging.Logger

abstract class CacheableRemoteResponse<ResultType>(
    private val forceRemoteRequests: Boolean = false
) {

    private val log = Logger.getLogger(CacheableRemoteResponse::class.java.name)

    private lateinit var flow: Flow<AsyncResult<ResultType>>
    private lateinit var deferredValue: Deferred<AsyncResult<ResultType>>

    private val response = object : RepositoryResponse<ResultType> {
        override fun flow() = flow
        override fun valueAsync() = deferredValue
    }

    suspend fun build(): RepositoryResponse<ResultType> {
        flow = channelFlow {
            coroutineScope {
                deferredValue = async {
                    send(AsyncResult.loading(null))
                    val dbResult = loadFromLocal()
                    val finalValue: AsyncResult<ResultType>
                    finalValue =
                        if (dbResult == null || forceRemoteRequests || shouldRequestFromRemote(
                                dbResult
                            )
                        ) {
                            try {
                                logDebug("Fetch data from network")
                                send(AsyncResult.loading(dbResult)) // Dispatch latest value quickly (UX purpose)
                                val networkResponse = fetchFromNetwork()
                                AsyncResult.success(networkResponse)
                            } catch (e: Exception) {
                                logDebug( "An error happened: ", e)
                                val asyncError = (e as? KoreException)?.asyncError
                                    ?: AsyncError.UnknownError("An error happened", e)
                                AsyncResult.error(asyncError, dbResult)
                            }
                        } else {
                            logDebug("Return data from local database")
                            AsyncResult.success(dbResult)
                        }
                    send(finalValue)
                    finalValue
                }
            }
        }
        return response
    }

    private suspend fun fetchFromNetwork(): ResultType {
        val networkResponse = requestRemoteCall()
        logDebug("Data fetched from network")
        saveRemoteResponse(networkResponse)
        return networkResponse
    }

    protected abstract suspend fun loadFromLocal(): ResultType?

    protected abstract fun shouldRequestFromRemote(localResponse: ResultType?): Boolean

    protected abstract suspend fun requestRemoteCall(): ResultType

    protected abstract suspend fun saveRemoteResponse(remoteResponse: ResultType)
}
