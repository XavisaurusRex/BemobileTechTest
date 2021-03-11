package cat.devsofthecoast.bemobiletechtest.common.domain.model

sealed class ResponseWrapper<out R> {
    data class Success<out S>(val data: S) : ResponseWrapper<S>()
    data class Error(val throwable: Throwable = Exception()) : ResponseWrapper<Nothing>()
}