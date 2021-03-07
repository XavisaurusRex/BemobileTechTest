package cat.devsofthecoast.bemobiletechtest.common.di.app

import android.content.Context
import cat.devsofthecoast.bemobiletechtest.App
import cat.devsofthecoast.bemobiletechtest.BuildConfig
import cat.devsofthecoast.bemobiletechtest.common.domain.AppDispatchers
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.remote.TransactionsRemoteDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.remote.impl.TransactionsRemoteDataSourceImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.TransactionRepository
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.impl.TransactionRepositoryImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.ws.TransactionsWs
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

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
        fun appDispatchersProvider() =
            AppDispatchers(Dispatchers.Main, Dispatchers.IO)

        @Provides
        @Singleton
        fun providesTransactionsRepository(@TransactionsQuietStone retrofit: Retrofit): TransactionsWs =
            retrofit.create(TransactionsWs::class.java)

        @Provides
        @Singleton
        @TransactionsQuietStone
        fun provideRetrofit(
            @TransactionsQuietStone okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_QUIET_STONE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        @TransactionsQuietStone
        fun provideOkHttpClient(): OkHttpClient {
            val httpClientBuilder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClientBuilder.addInterceptor(logging)
            }
            return httpClientBuilder.build()
        }


        private const val BASE_QUIET_STONE = "http://quiet-stone-2094.herokuapp.com/"

    }
}