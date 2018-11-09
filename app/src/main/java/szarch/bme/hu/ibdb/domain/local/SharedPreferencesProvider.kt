package szarch.bme.hu.ibdb.domain.local

class SharedPreferencesProvider private constructor() {

    companion object {
        private val SHARED_PREFERENCES_PROVIDER: SharedPreferencesProvider = SharedPreferencesProvider()

        fun getInstance(): SharedPreferencesProvider {
            return SHARED_PREFERENCES_PROVIDER
        }
    }


}