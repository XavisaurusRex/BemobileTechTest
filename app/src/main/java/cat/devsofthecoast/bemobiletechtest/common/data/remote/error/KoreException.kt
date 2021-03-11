package cat.devsofthecoast.bemobiletechtest.common.data.remote.error

class KoreException(val asyncError: AsyncError) : Exception() {
    override fun toString(): String {
        return asyncError.toString()
    }
}