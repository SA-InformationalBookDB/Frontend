package szarch.bme.hu.ibdb.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val sharedPreferencesProvider: SharedPreferencesProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val accessToken = sharedPreferencesProvider.getClientAccessToken()
        if (accessToken.isNotEmpty()) {
            val builder = request.newBuilder()
                .addHeader("Authorization", accessToken)
            request = builder.build()
        }
        return chain.proceed(request)
    }

}