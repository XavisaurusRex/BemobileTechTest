package cat.devsofthecoast.bemobiletechtest.common.di.app

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.remote.TransactionsRemoteDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.remote.impl.TransactionsRemoteDataSourceImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.TransactionRepository
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.impl.TransactionRepositoryImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.ws.TransactionsWs
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun transactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    abstract fun transactionsRemoteDataSource(
        transactionsRemoteDataSourceImpl: TransactionsRemoteDataSourceImpl
    ): TransactionsRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun providesTransactionsRepository(@TransactionsQuietStone retrofit: Retrofit): TransactionsWs =
            retrofit.create(TransactionsWs::class.java)
    }

}