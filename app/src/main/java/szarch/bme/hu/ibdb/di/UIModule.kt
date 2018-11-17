package szarch.bme.hu.ibdb.di

import dagger.Module
import dagger.Provides
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.repository.AdminRepository
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.activities.ActivitiesPresenter
import szarch.bme.hu.ibdb.ui.favourites.FavouritePresenter
import szarch.bme.hu.ibdb.ui.main.fragment.MainScreenPresenter
import szarch.bme.hu.ibdb.ui.search.SearchPresenter
import szarch.bme.hu.ibdb.ui.users.UsersPresenter
import javax.inject.Singleton

@Module
class UIModule {

    @Provides
    @Singleton
    fun activitiesPresenter(reviewInteractor: ReviewInteractor, oauthInteractor: OauthInteractor) =
        ActivitiesPresenter(reviewInteractor, oauthInteractor)

    @Provides
    @Singleton
    fun favouritesPresenter(oauthInteractor: OauthInteractor, userInteractor: UserInteractor) =
        FavouritePresenter(oauthInteractor, userInteractor)

    @Provides
    @Singleton
    fun mainPresenter(
        bookInteractor: BookInteractor,
        oauthInteractor: OauthInteractor,
        userInteractor: UserInteractor
    ) = MainScreenPresenter(bookInteractor, oauthInteractor, userInteractor)

    @Provides
    @Singleton
    fun searchPresenter(bookRepository: BookRepository) = SearchPresenter(bookRepository)

    @Provides
    @Singleton
    fun usersPresenter(adminRepository: AdminRepository) = UsersPresenter(adminRepository)


}