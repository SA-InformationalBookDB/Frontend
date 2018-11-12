package szarch.bme.hu.ibdb.di

import dagger.Module
import dagger.Provides
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.network.repository.UserRepository
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
    fun favouritesPresenter(userRepository: UserRepository) = FavouritePresenter(userRepository)

    @Provides
    @Singleton
    fun mainPresenter(bookRepository: BookRepository) = MainScreenPresenter(bookRepository)

    @Provides
    @Singleton
    fun searchPresenter(bookRepository: BookRepository) = SearchPresenter(bookRepository)

    @Provides
    @Singleton
    fun usersPresenter() = UsersPresenter()


}