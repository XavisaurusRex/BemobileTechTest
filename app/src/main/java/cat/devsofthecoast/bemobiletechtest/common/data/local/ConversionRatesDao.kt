package cat.devsofthecoast.bemobiletechtest.common.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cat.devsofthecoast.bemobiletechtest.common.data.local.model.ConversionRateDbo

@Dao
abstract class ConversionRatesDao {

    @Query("SELECT * FROM conversion_rates")
    abstract suspend fun getAllTConversionRates(): List<ConversionRateDbo>

    @Query("DELETE FROM conversion_rates")
    abstract suspend fun clearTable()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveConversionRate(conversionRateDbo: ConversionRateDbo): Long

}