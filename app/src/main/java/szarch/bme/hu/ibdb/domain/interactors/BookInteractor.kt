package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.repository.BookRepository
import javax.inject.Inject

class BookInteractor @Inject constructor(
    private val bookRepository: BookRepository
) {


}