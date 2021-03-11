package cat.devsofthecoast.bemobiletechtest.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cat.devsofthecoast.bemobiletechtest.common.data.local.converter.BigDecimalTypeConverter
import cat.devsofthecoast.bemobiletechtest.common.data.local.model.ConversionRateDbo
import cat.devsofthecoast.bemobiletechtest.common.data.local.model.TransactionDbo

@Database(
    entities = [TransactionDbo::class, ConversionRateDbo::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(BigDecimalTypeConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun transactionsDao(): TransactionsDao

    abstract fun conversionRatesDao(): ConversionRatesDao

    companion object {
        private const val DATABASE_NAME = "RoomDatabase.db"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
