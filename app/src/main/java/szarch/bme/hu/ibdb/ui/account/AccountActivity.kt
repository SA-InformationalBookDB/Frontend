package szarch.bme.hu.ibdb.ui.account

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ViewFlipper
import kotlinx.android.synthetic.main.activity_account.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.AuthenticationResult
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
    private var isRegistration: Boolean = true

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
            showLoginDialog()
        }

        btn_sign_in.setOnClickListener {
            showSignInDialog()
        }

        btn_sign_out.setOnClickListener {

        }
    }

    private fun showLoginDialog() {
        isRegistration = true
        dialogView = layoutInflater.inflate(R.layout.layout_login_dialog, null)
        dialog = AlertDialog.Builder(ContextThemeWrapper(this, R.style.PreferenceScreen))
            .setView(dialogView)
            .setTitle(R.string.login_text)
            .setCancelable(false)
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
        isRegistration = true
        dialogView = layoutInflater.inflate(R.layout.layout_registration_dialog, null)
        dialog = AlertDialog.Builder(ContextThemeWrapper(this, R.style.PreferenceScreen))
            .setView(dialogView)
            .setTitle(R.string.sign_up_text)
            .setCancelable(false)
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

    override fun showRegistrationResult(authenticationResult: AuthenticationResult) {
        dialogView.findViewById<ViewFlipper>(R.id.vf_registration).displayedChild = 2
        dialogView.findViewById<EditText>(R.id.tv_registration_result).setText(authenticationResult.message)
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).text = resources.getText(R.string.close_text)
        if (authenticationResult.isSuccessful) {
            dialogView.findViewById<ImageView>(R.id.iv_registration_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_success))
        } else {
            dialogView.findViewById<ImageView>(R.id.iv_registration_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_error))
            dialogView.findViewById<EditText>(R.id.tv_registration_error_reason).visibility = View.VISIBLE
            dialogView.findViewById<EditText>(R.id.tv_registration_error_reason)
                .setText(authenticationResult.message)
        }
    }

    override fun showLoginResult(authenticationResult: AuthenticationResult) {
        dialogView.findViewById<ViewFlipper>(R.id.vf_login).displayedChild = 2
        dialogView.findViewById<EditText>(R.id.tv_login_result).setText(authenticationResult.message)
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).text = resources.getText(R.string.close_text)
        if (authenticationResult.isSuccessful) {
            dialogView.findViewById<ImageView>(R.id.iv_login_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_success))
        } else {
            dialogView.findViewById<ImageView>(R.id.iv_login_result)
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_error))
            dialogView.findViewById<EditText>(R.id.tv_login_error_reason).visibility = View.VISIBLE
            dialogView.findViewById<EditText>(R.id.tv_login_error_reason).setText(authenticationResult.message)
        }
    }

}
