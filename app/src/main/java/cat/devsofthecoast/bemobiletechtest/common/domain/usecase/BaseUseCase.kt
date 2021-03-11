package cat.devsofthecoast.bemobiletechtest.common.domain.usecase

import androidx.lifecycle.LiveData
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult

interface BaseUseCase<T> {
    suspend fun execute(): LiveData<AsyncResult<T>>
}