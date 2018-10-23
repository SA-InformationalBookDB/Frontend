package szarch.bme.hu.ibdb.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class InjectedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }
}