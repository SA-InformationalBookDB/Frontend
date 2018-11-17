package szarch.bme.hu.ibdb.ui.activities

import szarch.bme.hu.ibdb.network.models.review.ReviewResponse

interface ActivitiesScreen {
    fun showActivities(reviewList: List<ReviewResponse>)
    fun showErrorMessage(message: String?)
}