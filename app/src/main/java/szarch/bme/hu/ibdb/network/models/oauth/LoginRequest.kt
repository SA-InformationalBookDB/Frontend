package szarch.bme.hu.ibdb.network.models.oauth

data class LoginRequest(
    val email: String,
    val password: String,
    val clientId: String,
    val redirectUri: String
)