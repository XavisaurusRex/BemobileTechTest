package cat.devsofthecoast.bemobiletechtest.common.data.remote.error

import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.AsyncError

class KoreException(val asyncError: AsyncError) : Exception() {
    override fun toString(): String {
        return asyncError.toString()
    }
}