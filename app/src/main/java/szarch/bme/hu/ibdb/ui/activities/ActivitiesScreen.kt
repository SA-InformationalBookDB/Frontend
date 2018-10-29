package szarch.bme.hu.ibdb.ui.activities

import szarch.bme.hu.ibdb.domain.models.Review

interface ActivitiesScreen {
    fun showActivities(reviewList: List<Review>)
}