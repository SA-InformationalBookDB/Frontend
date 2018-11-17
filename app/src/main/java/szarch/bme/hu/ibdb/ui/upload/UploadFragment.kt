package szarch.bme.hu.ibdb.ui.upload

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.content_upload.*
import kotlinx.android.synthetic.main.fragment_upload.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.category.CategoryResponse
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import java.util.*
import javax.inject.Inject


class UploadFragment : androidx.fragment.app.Fragment(), UploadScreen {

    @Inject
    lateinit var uploadPresenter: UploadPresenter

    private var categoryList: List<CategoryResponse> = listOf()
    private val selectedCategoryIds: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_upload, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadPresenter.attachScreen(this)
        uploadPresenter.getCategories()
        setupCategoryButton()
        setupSendButton()
    }

    override fun onStop() {
        uploadPresenter.detachScreen()
        super.onStop()
    }

    private fun injectFragment() {
        (context?.applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from BaseApplication")
    }

    private fun setupCategoryButton() {
        if (categoryList.isNotEmpty()) {
            val builder = AlertDialog.Builder(requireContext())
            val title = builder.setTitle(resources.getString(R.string.category_selection_text))

            val bookCategories = categoryList.map { it -> it.name }.toTypedArray()
            val checkedItemList = BooleanArray(bookCategories.size) { false }
            builder.setMultiChoiceItems(
                bookCategories, checkedItemList
            ) { dialog: DialogInterface, which: Int, isChecked: Boolean ->
                if (isChecked) {
                    if (isChecked) {
                        selectedCategoryIds.add(categoryList[which].id)
                    } else {
                        selectedCategoryIds.removeAt(selectedCategoryIds.indexOfFirst { it -> it == categoryList[which].id })
                    }
                }
            }
            builder.setPositiveButton(resources.getString(R.string.dialog_ok_text), null)
            builder.setNegativeButton(resources.getString(R.string.cancel_text), null)

            val dialog = builder.create()
            dialog.show()
        } else {
            Toast.makeText(requireContext(), resources.getString(R.string.category_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun setupSendButton() {
        fab_send.setOnClickListener {
            uploadPresenter.uploadBook(
                title = et_upload_book_title.text.toString(),
                author = et_upload_book_author.text.toString(),
                published = Date(),
                publisher = et_upload_book_publisher.text.toString(),
                imageUrl = et_upload_book_image_url.text.toString(),
                pageNumber = et_upload_book_page_number.text.toString().toInt(),
                sold = et_upload_book_sold.text.toString().toInt(),
                summary = et_upload_book_summary.text.toString()
            )
        }
    }

    override fun showUplaodMesaage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun setCategories(categories: List<CategoryResponse>) {
        this.categoryList = categories
    }

    companion object {
        val TAG = UploadFragment::class.java.simpleName
    }

}