package cat.devsofthecoast.bemobiletechtest.common.di.app

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.ConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.TransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl.CollectAndCalculateTransactionsMapperImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl.ConversionRatesMapperImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl.TransactionListMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun transactionMapper(
        collectAndCalculateTransactionsMapperImpl: CollectAndCalculateTransactionsMapperImpl
    ): CollectAndCalculateTransactionsMapper

    @Binds
    abstract fun conversionRatesMapper(
        ratesMapperImpl: ConversionRatesMapperImpl
    ): ConversionRatesMapper

    @Binds
    abstract fun transactionListMapper(
        transactionListMapperImpl: TransactionListMapperImpl
    ): TransactionListMapper
}