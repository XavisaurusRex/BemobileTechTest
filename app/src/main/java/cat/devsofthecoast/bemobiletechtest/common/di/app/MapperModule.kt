package cat.devsofthecoast.bemobiletechtest.common.di.app

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.ConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.LocalTransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.RemoteTransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl.CollectAndCalculateTransactionsMapperImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl.ConversionRatesMapperImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl.LocalTransactionListMapperImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl.RemoteTransactionListMapperImpl
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
    abstract fun remoteTransactionListMapperImpl(
        remoteTransactionListMapperImpl: RemoteTransactionListMapperImpl
    ): RemoteTransactionListMapper

    @Binds
    abstract fun localTransactionListMapper(
        localTransactionListMapperImpl: LocalTransactionListMapperImpl
    ): LocalTransactionListMapper


}