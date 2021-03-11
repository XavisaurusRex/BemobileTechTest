package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.impl

import cat.devsofthecoast.bemobiletechtest.common.data.local.TransactionDao
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.TransactionsLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.LocalTransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import javax.inject.Inject

class TransactionsLocalDataSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val localTransactionListMapper: LocalTransactionListMapper
) : TransactionsLocalDataSource {

    override suspend fun getTransactionList(): List<Transaction> {
        return localTransactionListMapper.mapTo(
            transactionDao.getAllTransactions()
        )
    }

    override suspend fun saveTransactionList(transactions: List<Transaction>) {
        transactionDao.clearTable()
        localTransactionListMapper
            .mapFrom(transactions)
            .forEach { transactionDbo ->
                transactionDao.saveTransaction(transactionDbo)
            }
    }
}