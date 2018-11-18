package szarch.bme.hu.ibdb.network.models.base

data class AuthenticationResult(val isSuccessful: Boolean, val message: String, val reason: String = "")