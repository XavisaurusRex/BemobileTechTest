package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper

import cat.devsofthecoast.bemobiletechtest.common.data.local.model.TransactionDbo
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction

interface LocalTransactionListMapper : ModelMapper<List<TransactionDbo>, List<Transaction>> {
    fun mapFrom(transactions: List<Transaction>): List<TransactionDbo>
}