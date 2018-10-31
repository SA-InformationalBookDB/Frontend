package szarch.bme.hu.ibdb.ui.account

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.ui.settings.SettingsPreferenceFragment

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        showSettingsFragment()
    }

    private fun showSettingsFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.settings_fragment_container, SettingsPreferenceFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}
