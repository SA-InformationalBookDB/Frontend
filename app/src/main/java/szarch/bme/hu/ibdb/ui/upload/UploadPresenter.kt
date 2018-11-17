package szarch.bme.hu.ibdb.ui.upload

import android.content.res.Resources
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.interactors.AdminInteractor
import szarch.bme.hu.ibdb.domain.interactors.CategoryInteractor
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import java.util.*
import javax.inject.Inject

class UploadPresenter @Inject constructor(
    private val adminInteractor: AdminInteractor,
    private val categoryInteractor: CategoryInteractor,
    private val resources: Resources
) : Presenter<UploadScreen>() {

    fun uploadBook(
        title: String, author: String, published: Date? = null, publisher: String? = null,
        imageUrl: String? = null, summary: String, pageNumber: Int, sold: Int? = null
    ) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            screen?.showUplaodMesaage(resources.getString(R.string.book_upload_unsuccessful))
        })
        {
            adminInteractor.addBook(title, author, published, publisher, imageUrl, summary, pageNumber, sold)
            screen?.showUplaodMesaage(resources.getString(R.string.book_upload_successful))
        }
    }

    fun getCategories() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            //Error handling
        }) {
            screen?.setCategories(categoryInteractor.getCategories())
        }
    }

}