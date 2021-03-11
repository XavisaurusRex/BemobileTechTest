package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.RemoteTransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import javax.inject.Inject

class RemoteTransactionListMapperImpl @Inject constructor() : RemoteTransactionListMapper {

    override fun mapTo(from: List<ApiTransaction>): List<Transaction> {
        return from.map {
            it.amount.toBigDecimal()
            Transaction(
                it.skuStockRef,
                it.amount.toBigDecimal(),
                it.currency
            )
        }
    }

}