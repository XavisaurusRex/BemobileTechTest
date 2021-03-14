package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction

interface TransactionsLocalDataSource {
    suspend fun getTransactionList(): List<Transaction>

    suspend fun getTransactionDetailsForProduct(skuRefCode: String): List<Transaction>

    suspend fun saveTransactionList(transactions: List<Transaction>)
}
