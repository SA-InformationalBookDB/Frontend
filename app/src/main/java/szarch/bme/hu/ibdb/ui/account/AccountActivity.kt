package szarch.bme.hu.ibdb.ui.account

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_account.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.base.AuthenticationResult
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import szarch.bme.hu.ibdb.ui.settings.SettingsPreferenceFragment
import szarch.bme.hu.ibdb.util.StringUtil
import javax.inject.Inject

class AccountActivity : AppCompatActivity(), AccountScreen {

    @Inject
    lateinit var accountPresenter: AccountPresenter

    private var dialogState: DialogState = DialogState.INPUT
    private lateinit var dialogView: View
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_account)
        setupScreen()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            setResult(Activity.RESULT_OK)
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
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

    private fun setupScreen() {
        accountPresenter.getUserAuthenticationInfo()
    }

    private fun setupButtons() {
        btn_login.setOnClickListener {
            showLoginDialog()
        }

        btn_sign_in.setOnClickListener {
            showSignInDialog()
        }

        btn_sign_out.setOnClickListener {
            accountPresenter.logoutUser()
        }
    }

    private fun showLoginDialog() {
        dialogView = layoutInflater.inflate(R.layout.layout_login_dialog, null)
        dialog = AlertDialog.Builder(ContextThemeWrapper(this, R.style.PreferenceScreen))
            .setView(dialogView)
            .setTitle(R.string.login_text)
            .setPositiveButton(R.string.login_text, null)
            .create()
        dialog.setOnShowListener {
            val b = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            b.setOnClickListener {
                val emailAddress = dialogView.findViewById<EditText>(R.id.et_login_user_name)
                val password = dialogView.findViewById<EditText>(R.id.et_login_password)
                when (dialogState) {
                    DialogState.INPUT -> {
                        var isValid = true
                        if (!StringUtil.checkEmailValidity(emailAddress.text.toString())) {
                            emailAddress.error = resources.getString(R.string.email_address_not_valid)
                            isValid = false
                        }

                        if (!StringUtil.checkPasswordValidatity(password.text.toString())) {
                            password.error = resources.getString(R.string.password_not_valid)
                            isValid = false
                        }
                        if (isValid) {
                            dialogState = DialogState.LOADING
                            accountPresenter.loginUser(
                                emailAddress.text.toString(),
                                password.text.toString()
                            )
                            dialogView.findViewById<ViewFlipper>(R.id.vf_login).displayedChild = 1
                            b.setText(R.string.cancel_text)
                        }
                    }

                    DialogState.LOADING -> {
                        dialogState = DialogState.INPUT
                        dialog.dismiss()
                    }

                    DialogState.RESULT -> {
                        dialogState = DialogState.INPUT
                        dialog.dismiss()
                    }
                }
            }
        }
        dialog.show()
    }

    private fun showSignInDialog() {
        dialogView = layoutInflater.inflate(R.layout.layout_registration_dialog, null)
        dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle(R.string.sign_up_text)
            .setPositiveButton(R.string.sign_up_text, null)
            .create()
        dialog.setOnShowListener {
            val b = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            b.setOnClickListener {
                val emailAddress = dialogView.findViewById<EditText>(R.id.et_registration_user_name)
                val password = dialogView.findViewById<EditText>(R.id.et_registration_password)
                val confirmPassword = dialogView.findViewById<EditText>(R.id.et_registration_confirm_password)
                when (dialogState) {
                    DialogState.INPUT -> {
                        var isValid = true
                        if (!StringUtil.checkEmailValidity(emailAddress.text.toString())) {
                            emailAddress.error = resources.getString(R.string.email_address_not_valid)
                            isValid = false
                        }

                        if (!StringUtil.checkPasswordValidatity(password.text.toString())) {
                            password.error = resources.getString(R.string.password_not_valid)
                            isValid = false
                        } else {
                            if (!StringUtil.checkIsTextAreEqual(
                                    password.text.toString(),
                                    confirmPassword.text.toString()
                                )
                            ) {
                                password.error = resources.getString(R.string.password_not_equals)
                                confirmPassword.error = resources.getString(R.string.password_not_equals)
                                isValid = false
                            }
                        }
                        if (isValid) {
                            dialogState = DialogState.LOADING
                            accountPresenter.registerUser(
                                emailAddress.text.toString(),
                                password.text.toString(),
                                confirmPassword.text.toString()
                            )
                            dialogView.findViewById<ViewFlipper>(R.id.vf_registration).displayedChild = 1
                            b.setText(R.string.cancel_text)
                        }
                    }

                    DialogState.LOADING -> {
                        dialogState = DialogState.INPUT
                        dialog.dismiss()
                    }

                    DialogState.RESULT -> {
                        dialogState = DialogState.INPUT
                        dialog.dismiss()
                    }
                }
            }
        }
        dialog.show()
    }

    private fun showAuthenticationStatus(isUserAuthenticated: Boolean) {
        tv_intro.text =
                if (isUserAuthenticated) resources.getString(R.string.account_signed_in_title) else resources.getString(
                    R.string.account_signed_out_title
                )
        cl_signed_in.isVisible = isUserAuthenticated
        cl_signed_out.isVisible = isUserAuthenticated.not()
    }

    override fun showRegistrationResult(authenticationResult: AuthenticationResult) {
        dialogState = DialogState.RESULT
        dialogView.findViewById<ViewFlipper>(R.id.vf_registration).displayedChild = 2
        dialogView.findViewById<TextView>(R.id.tv_registration_result).text = authenticationResult.message
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).text = resources.getText(R.string.close_text)
        if (authenticationResult.isSuccessful) {
            dialogView.findViewById<ImageView>(R.id.iv_registration_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_success))
        } else {
            dialogView.findViewById<ImageView>(R.id.iv_registration_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_error))
            dialogView.findViewById<TextView>(R.id.tv_registration_error_reason).visibility = View.VISIBLE
            dialogView.findViewById<TextView>(R.id.tv_registration_error_reason).text = authenticationResult.message
        }
    }

    override fun showLoginResult(authenticationResult: AuthenticationResult) {
        dialogState = DialogState.RESULT
        dialogView.findViewById<ViewFlipper>(R.id.vf_login).displayedChild = 2
        dialogView.findViewById<TextView>(R.id.tv_login_result).text = authenticationResult.message
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).text = resources.getText(R.string.close_text)
        if (authenticationResult.isSuccessful) {
            dialogView.findViewById<ImageView>(R.id.iv_login_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_success))
            accountPresenter.getUser()
            showAuthenticationStatus(true)
        } else {
            dialogView.findViewById<ImageView>(R.id.iv_login_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_error))
            dialogView.findViewById<TextView>(R.id.tv_login_error_reason).visibility = View.VISIBLE
            dialogView.findViewById<TextView>(R.id.tv_login_error_reason).text = authenticationResult.message
        }
    }

    override fun showLogoutResult(authenticationResult: AuthenticationResult) {
        Toast.makeText(applicationContext, authenticationResult.message, Toast.LENGTH_LONG).show()
        showAuthenticationStatus(false)
    }

    override fun showIsUserAuthenticated(isUserAuthenticated: Boolean) {
        showAuthenticationStatus(isUserAuthenticated)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
    }


}