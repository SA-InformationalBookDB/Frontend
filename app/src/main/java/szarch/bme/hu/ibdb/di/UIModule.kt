package szarch.bme.hu.ibdb.di

import dagger.Module
import dagger.Provides
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
    fun activitiesPresenter() = ActivitiesPresenter()

    @Provides
    @Singleton
    fun favouritesPresenter() = FavouritePresenter()

    @Provides
    @Singleton
    fun mainPresenter() = MainScreenPresenter()

    @Provides
    @Singleton
    fun searchPresenter() = SearchPresenter()

    @Provides
    @Singleton
    fun usersPresenter() = UsersPresenter()

}