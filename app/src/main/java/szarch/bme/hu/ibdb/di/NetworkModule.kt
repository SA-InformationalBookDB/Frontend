package szarch.bme.hu.ibdb.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import szarch.bme.hu.ibdb.BuildConfig
import szarch.bme.hu.ibdb.network.api.*
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
    fun adminApi(retrofit: Retrofit): AdminApi = retrofit.create(AdminApi::class.java)

    @Provides
    @Singleton
    fun bookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)

    @Provides
    @Singleton
    fun categoryApi(retrofit: Retrofit): CategoryApi = retrofit.create(CategoryApi::class.java)

    @Provides
    @Singleton
    fun oauthApi(retrofit: Retrofit): OauthApi = retrofit.create(OauthApi::class.java)

    @Provides
    @Singleton
    fun reviewApi(retrofit: Retrofit): ReviewApi = retrofit.create(ReviewApi::class.java)

    @Provides
    @Singleton
    fun userApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

}