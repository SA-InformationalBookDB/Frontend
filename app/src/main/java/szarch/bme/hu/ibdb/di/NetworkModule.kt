package szarch.bme.hu.ibdb.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import szarch.bme.hu.ibdb.BuildConfig
import szarch.bme.hu.ibdb.network.api.OauthApi
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.SZARCH_IBDB_API_BASE_URL)
        .build()

    @Provides
    @Singleton
    fun oauthApi(retrofit: Retrofit): OauthApi = retrofit.create(OauthApi::class.java)

}