package szarch.bme.hu.ibdb.ui.activities

import szarch.bme.hu.ibdb.network.models.review.Review

interface ActivitiesScreen {
    fun showActivities(reviewList: List<Review>)
    fun showErrorMessage(message: String?)
}