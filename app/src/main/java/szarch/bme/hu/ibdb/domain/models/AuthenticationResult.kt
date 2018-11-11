package szarch.bme.hu.ibdb.domain.models

data class AuthenticationResult(val isSuccessful: Boolean, val message: String, val reason: String = "")