package cat.devsofthecoast.bemobiletechtest.common.data.remote

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface RepositoryResponse<ResultType> {

    fun flow(): Flow<AsyncResult<ResultType>>

    fun valueAsync(): Deferred<AsyncResult<ResultType>>

}