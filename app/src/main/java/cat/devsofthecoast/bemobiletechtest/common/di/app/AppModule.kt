package cat.devsofthecoast.bemobiletechtest.common.di.app

import cat.devsofthecoast.bemobiletechtest.BuildConfig
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.TransactionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesTransactionsRepository(@TransactionsQuietStone retrofit: Retrofit): TransactionsRepository =
        retrofit.create(TransactionsRepository::class.java)

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

    companion object {
        const val BASE_QUIET_STONE = "http://quiet-stone-2094.herokuapp.com/"
    }

}