package cat.devsofthecoast.bemobiletechtest.common.di.app

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.*
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl.*
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.mapper.TransactionDetailsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.mapper.impl.TransactionDetailsMapperImpl
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
        ratesMapperImpl: RemoteConversionRatesMapperImpl
    ): RemoteConversionRatesMapper

    @Binds
    abstract fun remoteTransactionListMapperImpl(
        remoteTransactionListMapperImpl: RemoteTransactionListMapperImpl
    ): RemoteTransactionListMapper

    @Binds
    abstract fun localTransactionListMapper(
        localTransactionListMapperImpl: LocalTransactionListMapperImpl
    ): LocalTransactionListMapper

    @Binds
    abstract fun localConversionRatesMapper(
        localConversionRatesMapper: LocalConversionRatesMapperImpl
    ): LocalConversionRatesMapper

    @Binds
    abstract fun transactionDetailsMapper(
        transactionDetailsMapperImpl: TransactionDetailsMapperImpl
    ): TransactionDetailsMapper


}