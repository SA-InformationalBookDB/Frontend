package szarch.bme.hu.ibdb.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.ui.base.BaseApplication

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_settings)
    }

    private fun injectActivity() {
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from BaseApplication")
    }

}
