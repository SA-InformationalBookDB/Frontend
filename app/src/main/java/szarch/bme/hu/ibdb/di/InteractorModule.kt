package szarch.bme.hu.ibdb.di

import dagger.Module
import dagger.Provides
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.repository.OauthRepository
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun oauthInteractor(oauthRepository: OauthRepository, sharedPreferencesProvider: SharedPreferencesProvider) =
        OauthInteractor(oauthRepository, sharedPreferencesProvider)

}