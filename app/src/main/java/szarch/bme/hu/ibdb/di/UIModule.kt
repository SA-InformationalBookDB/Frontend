package szarch.bme.hu.ibdb.di

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import szarch.bme.hu.ibdb.domain.interactors.*
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.activities.ActivitiesPresenter
import szarch.bme.hu.ibdb.ui.favourites.FavouritePresenter
import szarch.bme.hu.ibdb.ui.main.fragment.MainScreenPresenter
import szarch.bme.hu.ibdb.ui.reviews.ReviewsPresenter
import szarch.bme.hu.ibdb.ui.search.SearchPresenter
import szarch.bme.hu.ibdb.ui.settings.SettingsPreferencePresenter
import szarch.bme.hu.ibdb.ui.upload.UploadPresenter
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
    fun uploadPresenter(
        adminInteractor: AdminInteractor,
        categoryInteractor: CategoryInteractor,
        resources: Resources
    ) = UploadPresenter(adminInteractor, categoryInteractor, resources)

    @Provides
    @Singleton
    fun usersPresenter(adminInteractor: AdminInteractor) = UsersPresenter(adminInteractor)

    @Provides
    @Singleton
    fun reviewPresenter(reviewInteractor: ReviewInteractor, oauthInteractor: OauthInteractor, resources: Resources) =
        ReviewsPresenter(reviewInteractor, oauthInteractor, resources)

    @Provides
    @Singleton
    fun settingsPreferencePresenter(categoryInteractor: CategoryInteractor, userInteractor: UserInteractor) =
        SettingsPreferencePresenter(categoryInteractor, userInteractor)


}