package szarch.bme.hu.ibdb.network.models.oauth

data class RegistrationRequest(val email: String, val password: String, val confirmPassword: String)