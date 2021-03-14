package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.impl

import cat.devsofthecoast.bemobiletechtest.common.data.local.TransactionsDao
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.TransactionsLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.LocalTransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import javax.inject.Inject

class TransactionsLocalDataSourceImpl @Inject constructor(
    private val transactionsDao: TransactionsDao,
    private val localTransactionListMapper: LocalTransactionListMapper
) : TransactionsLocalDataSource {

    override suspend fun getTransactionList(): List<Transaction> {
        return localTransactionListMapper.mapTo(
            transactionsDao.getAllTransactions()
        )
    }

    override suspend fun getTransactionDetailsForProduct(skuRefCode: String): List<Transaction> {
        return localTransactionListMapper.mapTo(
            transactionsDao.getTransactionsForProduct(skuRefCode)
        )
    }

    override suspend fun saveTransactionList(transactions: List<Transaction>) {
        transactionsDao.clearTable()
        localTransactionListMapper
            .mapFrom(transactions)
            .forEach { transactionDbo ->
                transactionsDao.saveTransaction(transactionDbo)
            }
    }
}