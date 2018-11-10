package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.models.oauth.LoginRequest
import szarch.bme.hu.ibdb.network.repository.OauthRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class OauthInteractor @Inject constructor(
    private val oauthRepository: OauthRepository
) {

    fun sendLoginRequest(loginRequest: LoginRequest) {
        GlobalScope.launch(Contexts.UI) {
            val loginResponse = oauthRepository.sendLogin(loginRequest)
            if (loginResponse != null) {

            }
        }
    }
}
