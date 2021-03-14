package cat.devsofthecoast.bemobiletechtest.common.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cat.devsofthecoast.bemobiletechtest.common.data.local.model.TransactionDbo

@Dao
abstract class TransactionsDao {

    @Query("SELECT * FROM transaction_history")
    abstract suspend fun getAllTransactions(): List<TransactionDbo>

    @Query("SELECT * FROM transaction_history WHERE skuRefCode = :skuRefId")
    abstract suspend fun getTransactionsForProduct(skuRefId: String): List<TransactionDbo>

    @Query("DELETE FROM transaction_history")
    abstract suspend fun clearTable()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveTransaction(transactionDbo: TransactionDbo): Long

}