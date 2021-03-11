package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.TransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import javax.inject.Inject

class TransactionListMapperImpl @Inject constructor() : TransactionListMapper {
    override fun mapToBo(from: List<ApiTransaction>): List<Transaction> {

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