package szarch.bme.hu.ibdb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import javax.inject.Singleton

@Module
class ProviderModule {

    @Provides
    @Singleton
    fun sharedPreferencesProvider(context: Context) = SharedPreferencesProvider(context)

}