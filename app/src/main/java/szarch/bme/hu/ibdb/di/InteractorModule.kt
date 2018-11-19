package szarch.bme.hu.ibdb.di

import dagger.Module
import dagger.Provides
import szarch.bme.hu.ibdb.domain.interactors.*
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.repository.*
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun adminInteractor(adminRepository: AdminRepository) = AdminInteractor(adminRepository)

    @Provides
    @Singleton
    fun bookInteractor(bookRepository: BookRepository) = BookInteractor(bookRepository)


    @Provides
    @Singleton
    fun categoryInteractor(categoryRepository: CategoryRepository) = CategoryInteractor(categoryRepository)

    @Provides
    @Singleton
    fun oauthInteractor(oauthRepository: OauthRepository, sharedPreferencesProvider: SharedPreferencesProvider) =
        OauthInteractor(oauthRepository, sharedPreferencesProvider)

    @Provides
    @Singleton
    fun reviewInteractor(reviewRepository: ReviewRepository) = ReviewInteractor(reviewRepository)

    @Provides
    @Singleton
    fun userInteractor(sharedPreferencesProvider: SharedPreferencesProvider, userRepository: UserRepository) =
        UserInteractor(sharedPreferencesProvider, userRepository)

}