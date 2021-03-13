package cat.devsofthecoast.bemobiletechtest.common.domain.usecase

import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<T> {
    suspend fun execute(): Flow<AsyncResult<T>>
}