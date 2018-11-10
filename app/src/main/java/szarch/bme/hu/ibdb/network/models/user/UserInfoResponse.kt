package szarch.bme.hu.ibdb.network.models.user

import java.util.*

data class UserInfoResponse(
    val id: String,
    val email: String,
    val role: Role,
    val nickname: String,
    val birthDate: Date
)