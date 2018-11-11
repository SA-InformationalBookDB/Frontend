package szarch.bme.hu.ibdb.network.models.oauth

import java.util.*

data class AccessTokenResponse(
    val accessToken: String,
    val accessTokenExpiration: Date,
    val refreshToken: String
)