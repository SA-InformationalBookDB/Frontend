package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.repository.UserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userRepository: UserRepository
) {


}