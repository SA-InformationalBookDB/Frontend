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

    override fun onStart() {
        super.onStart()
        settingsPreferencePresenter.attachScreen(this)
    }

    override fun onDestroy() {
        settingsPreferencePresenter.detachScreen()
        super.onDestroy()
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
        etpNickname = findPreference("pref_nickname") as EditTextPreference
        etpYearOfBirth = findPreference("pref_birth_year") as EditTextPreference
        etpCategory = findPreference("pref_category") as Preference
    }

    private fun setEditTextPreferencesSummaries() {
        etpNickname.summary = etpNickname.text
        etpYearOfBirth.summary = etpYearOfBirth.text
    }

    private fun setEditTextOnClickListeners() {

        etpNickname.setOnPreferenceChangeListener { _, any ->
            etpNickname.summary = any.toString()
            settingsPreferencePresenter.updateUserInfos(nickName = any.toString())
            true
        }

        etpYearOfBirth.setOnPreferenceChangeListener { _, any ->
            if (szarch.bme.hu.ibdb.util.StringUtil.isDateValid(any.toString())) {
                etpYearOfBirth.summary = any.toString()
                settingsPreferencePresenter.updateUserInfos(
                    birthDate = szarch.bme.hu.ibdb.util.StringUtil.createDateFromString(
                        any.toString()
                    )
                )
                true
            } else {
                false
            }

        }

        etpCategory.setOnPreferenceClickListener {
            showFavouriteDialog()
            true
        }
    }

    override fun showCategoryDialog(categoryList: List<CategoryResponse>) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(resources.getString(R.string.category_selection_text))

        val bookCategories = categoryList.map { it -> it.name }.toTypedArray()
        val checkedItemList = BooleanArray(bookCategories.size) { false }
        builder.setMultiChoiceItems(
            bookCategories, checkedItemList
        ) { dialog: DialogInterface, which: Int, isChecked: Boolean ->
            if (isChecked) {
                if (isChecked) {
                    categoryIds.add(categoryList[which].id)
                } else {
                    categoryIds.removeAt(categoryIds.indexOfFirst { it -> it == categoryList[which].id })
                }
            }
        }
        builder.setPositiveButton(resources.getString(R.string.dialog_ok_text)) { dialog, which ->
            settingsPreferencePresenter.updateUserCategories(categoryIds)
        }
        builder.setNegativeButton(resources.getString(R.string.cancel_text), null)

        val dialog = builder.create()
        dialog.show()
    }


    private fun showFavouriteDialog() {
        settingsPreferencePresenter.getCategories()
    }

    fun refreshScreen() {
        preferenceScreen = null
        addPreferencesFromResource(R.xml.settings)
        initializeEditTextPreferences()
        setEditTextPreferencesSummaries()
    }


}