package szarch.bme.hu.ibdb.ui.account

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_account.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import szarch.bme.hu.ibdb.ui.settings.SettingsPreferenceFragment
import javax.inject.Inject

class AccountActivity : AppCompatActivity(), AccountScreen {

    @Inject
    lateinit var accountPresenter: AccountPresenter

    private lateinit var dialogView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_account)
        showSettingsFragment()
        setupButtons()
    }

    override fun onStart() {
        super.onStart()
        accountPresenter.attachScreen(this)
    }

    override fun onStop() {
        accountPresenter.detachScreen()
        super.onStop()
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

    private fun setupButtons() {
        btn_login.setOnClickListener {

        }

        btn_sign_in.setOnClickListener {

        }

        btn_sign_out.setOnClickListener {

        }
    }


}
