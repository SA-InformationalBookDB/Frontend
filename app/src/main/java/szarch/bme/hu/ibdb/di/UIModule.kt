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

@Module
class UIModule {

    @Provides
    fun activitiesPresenter(
        reviewInteractor: ReviewInteractor,
        oauthInteractor: OauthInteractor,
        resources: Resources
    ) =
        ActivitiesPresenter(reviewInteractor, oauthInteractor, resources)

    @Provides
    fun favouritesPresenter(oauthInteractor: OauthInteractor, userInteractor: UserInteractor, resources: Resources) =
        FavouritePresenter(oauthInteractor, userInteractor, resources)

    @Provides
    fun mainPresenter(
        bookInteractor: BookInteractor,
        oauthInteractor: OauthInteractor,
        userInteractor: UserInteractor
    ) = MainScreenPresenter(bookInteractor, oauthInteractor, userInteractor)

    @Provides
    fun searchPresenter(bookRepository: BookRepository) = SearchPresenter(bookRepository)

    @Provides
    fun uploadPresenter(
        adminInteractor: AdminInteractor,
        categoryInteractor: CategoryInteractor,
        resources: Resources
    ) = UploadPresenter(adminInteractor, categoryInteractor, resources)

    @Provides
    fun usersPresenter(adminInteractor: AdminInteractor) = UsersPresenter(adminInteractor)

    @Provides
    fun reviewPresenter(reviewInteractor: ReviewInteractor, oauthInteractor: OauthInteractor, resources: Resources) =
        ReviewsPresenter(reviewInteractor, oauthInteractor, resources)

    @Provides
    fun settingsPreferencePresenter(categoryInteractor: CategoryInteractor, userInteractor: UserInteractor) =
        SettingsPreferencePresenter(categoryInteractor, userInteractor)


}