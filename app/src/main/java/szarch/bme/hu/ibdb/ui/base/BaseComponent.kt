package szarch.bme.hu.ibdb.ui.base

import szarch.bme.hu.ibdb.ui.account.AccountActivity
import szarch.bme.hu.ibdb.ui.activities.ActivitiesFragment
import szarch.bme.hu.ibdb.ui.detail.DetailActivity
import szarch.bme.hu.ibdb.ui.favourites.FavouritesFragment
import szarch.bme.hu.ibdb.ui.main.activity.MainActivity
import szarch.bme.hu.ibdb.ui.main.fragment.MainFragment
import szarch.bme.hu.ibdb.ui.reviews.ReviewsActivity
import szarch.bme.hu.ibdb.ui.search.SearchActivity
import szarch.bme.hu.ibdb.ui.settings.SettingsPreferenceFragment
import szarch.bme.hu.ibdb.ui.upload.UploadFragment
import szarch.bme.hu.ibdb.ui.users.UsersFragment

interface BaseComponent {

    fun inject(accountActivity: AccountActivity)

    fun inject(activitiesFragment: ActivitiesFragment)

    fun inject(detailActivity: DetailActivity)

    fun inject(favouritesFragment: FavouritesFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

    fun inject(reviewsActivity: ReviewsActivity)

    fun inject(searchActivity: SearchActivity)

    fun inject(settingsPreferenceFragment: SettingsPreferenceFragment)

    fun inject(uploadFragment: UploadFragment)

    fun inject(usersFragment: UsersFragment)

}