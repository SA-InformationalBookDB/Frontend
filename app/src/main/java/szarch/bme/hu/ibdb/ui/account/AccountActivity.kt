package szarch.bme.hu.ibdb.ui.account

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import szarch.bme.hu.ibdb.ui.settings.SettingsPreferenceFragment

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_account)
        showSettingsFragment()
    }

    private fun injectActivity() {
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }

    private fun showSettingsFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.settings_fragment_container, SettingsPreferenceFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}
