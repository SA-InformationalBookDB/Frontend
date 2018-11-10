package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.repository.ReviewRepository
import javax.inject.Inject

class ReviewInteractor @Inject constructor(
    private val reviewRepository: ReviewRepository
) {


}