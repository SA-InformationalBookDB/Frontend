package szarch.bme.hu.ibdb.network.models.user

data class UpdateUserRequest(
    val birthDate: String? = null,
    val nickname: String? = null
)