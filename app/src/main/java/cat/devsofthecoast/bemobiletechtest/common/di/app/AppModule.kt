package cat.devsofthecoast.bemobiletechtest.common.di.app

import cat.devsofthecoast.bemobiletechtest.BuildConfig
import cat.devsofthecoast.bemobiletechtest.common.domain.AppDispatchers
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
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {
        @Provides
        fun appDispatchersProvider() =
            AppDispatchers(Dispatchers.Main, Dispatchers.IO)

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