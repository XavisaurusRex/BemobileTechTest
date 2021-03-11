package cat.devsofthecoast.bemobiletechtest.common.di.app

import android.content.Context
import cat.devsofthecoast.bemobiletechtest.common.data.local.AppRoomDatabase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.ConversionRatesLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.TransactionsLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.impl.ConversionRatesLocalDataSourceImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.impl.TransactionsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun transactionsLocalDataSource(
        transactionsLocalDataSourceImpl: TransactionsLocalDataSourceImpl
    ): TransactionsLocalDataSource

    @Binds
    abstract fun conversionRatesLocalDataSource(
        conversionRatesLocalDataSourceImpl: ConversionRatesLocalDataSourceImpl
    ): ConversionRatesLocalDataSource

    companion object {

        @Provides
        @Singleton
        fun appRoomDatabaseProvider(@ApplicationContext context: Context) =
            AppRoomDatabase.buildDatabase(context)

        @Provides
        fun transactionsDaoProvider(database: AppRoomDatabase) =
            database.transactionsDao()

        @Provides
        fun conversionRatesDao(database: AppRoomDatabase) =
            database.conversionRatesDao()

    }
}