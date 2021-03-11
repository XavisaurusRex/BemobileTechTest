package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl

import cat.devsofthecoast.bemobiletechtest.common.data.local.model.TransactionDbo
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.LocalTransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import javax.inject.Inject

class LocalTransactionListMapperImpl @Inject constructor() : LocalTransactionListMapper {
    override fun mapFrom(transactions: List<Transaction>): List<TransactionDbo> {
        return transactions.map {
            TransactionDbo(
                null,
                it.skuRefCode,
                it.amount,
                it.currency
            )
        }
    }

    override fun mapTo(from: List<TransactionDbo>): List<Transaction> {
        return from.map {
            Transaction(
                it.skuRefCode,
                it.amount,
                it.currency
            )
        }
    }

}