package szarch.bme.hu.ibdb.network.models.user

data class UpdateUserRequest(
    val birthDate: String,
    val email: String,
    val nickname: String
)