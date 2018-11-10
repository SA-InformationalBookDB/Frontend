package szarch.bme.hu.ibdb.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext

object Contexts {
    val UI = Dispatchers.Main
    val NETWORK = newSingleThreadContext("NETWORK")
    val IO = newSingleThreadContext("IO")
}