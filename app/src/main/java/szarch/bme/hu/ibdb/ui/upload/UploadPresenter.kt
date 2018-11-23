package szarch.bme.hu.ibdb.ui.upload

import android.content.res.Resources
import android.util.Log
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.interactors.AdminInteractor
import szarch.bme.hu.ibdb.domain.interactors.CategoryInteractor
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class UploadPresenter @Inject constructor(
    private val adminInteractor: AdminInteractor,
    private val categoryInteractor: CategoryInteractor,
    private val resources: Resources
) : Presenter<UploadScreen>() {

    fun uploadBook(
        title: String, author: String, published: String? = null, publisher: String? = null,
        imageUrl: String? = null, summary: String, pageNumber: Int, sold: Int? = null
    ) {
        launch {
            try {
                Log.d("Testign", "Presenter sends")
                adminInteractor.addBook(title, author, published, publisher, imageUrl, summary, pageNumber, sold)
                screen?.showUplaodMesaage(resources.getString(R.string.book_upload_successful))
            } catch (e: Exception) {
                screen?.showUplaodMesaage(resources.getString(R.string.book_upload_unsuccessful))
            }
        }
    }

    fun getCategories() {
        launch {
            try {
                screen?.setCategories(categoryInteractor.getCategories())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}