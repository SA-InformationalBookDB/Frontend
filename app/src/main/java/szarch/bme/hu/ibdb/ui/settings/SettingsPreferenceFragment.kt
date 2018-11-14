package szarch.bme.hu.ibdb.ui.settings

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.category.CategoryResponse
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import javax.inject.Inject


class SettingsPreferenceFragment : PreferenceFragmentCompat(), SettingsPreferenceScreen {

    @Inject
    lateinit var settingsPreferencePresenter: SettingsPreferencePresenter

    private lateinit var etpEmailAddress: EditTextPreference
    private lateinit var etpNickname: EditTextPreference
    private lateinit var etpYearOfBirth: EditTextPreference
    private lateinit var etpCategory: Preference
    private var categoryIds: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFragment()
        settingsPreferencePresenter.attachScreen(this)
        initializeEditTextPreferences()
        setEditTextPreferencesSummaries()
        setEditTextOnClickListeners()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return if (preference?.key == "preference_favourite_categories") {
            showFavouriteDialog()
            return false
        } else {
            super.onPreferenceTreeClick(preference)
        }
    }

    private fun injectFragment() {
        (context?.applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from BaseApplication")
    }

    private fun initializeEditTextPreferences() {
        etpEmailAddress = findPreference("pref_email_address") as EditTextPreference
        etpNickname = findPreference("pref_nickname") as EditTextPreference
        etpYearOfBirth = findPreference("pref_birth_year") as EditTextPreference
        etpCategory = findPreference("pref_category") as Preference
    }

    private fun setEditTextPreferencesSummaries() {
        etpEmailAddress.summary = etpEmailAddress.text
        etpNickname.summary = etpNickname.text
        etpYearOfBirth.summary = etpYearOfBirth.text
    }

    private fun setEditTextOnClickListeners() {
        etpEmailAddress.setOnPreferenceChangeListener { _, any ->
            etpEmailAddress.summary = any.toString()
            true
        }
        etpNickname.setOnPreferenceChangeListener { _, any ->
            etpNickname.summary = any.toString()
            //   nav_header_user_name.text = any.toString()
            true
        }

        etpYearOfBirth.setOnPreferenceChangeListener { _, any ->
            etpYearOfBirth.summary = any.toString()
            true
        }

        etpCategory.setOnPreferenceClickListener {
            showFavouriteDialog()
            true
        }
    }

    override fun showCategoryDialog(categoryList: List<CategoryResponse>) {
        val builder = AlertDialog.Builder(requireContext())
        val title = builder.setTitle(resources.getString(R.string.category_selection_text))

        val bookCategories = categoryList.map { it -> it.name }.toTypedArray()
        val checkedItems = booleanArrayOf(false, false, false, false, false)
        builder.setMultiChoiceItems(
            bookCategories, checkedItems
        ) { dialog: DialogInterface, which: Int, isChecked: Boolean ->
            if (isChecked) {
                if (isChecked) {
                    categoryIds.add(categoryList[which].id)
                } else {
                    categoryIds.removeAt(categoryIds.indexOfFirst { it -> it == categoryList[which].id })
                }
            }
        }
        builder.setPositiveButton("OK") { dialog, which ->
            // user clicked OK
        }
        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }


    private fun showFavouriteDialog() {
        // setup the alert builder

    }

}