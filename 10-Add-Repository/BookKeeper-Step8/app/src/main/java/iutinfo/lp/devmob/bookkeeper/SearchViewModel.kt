package iutinfo.lp.devmob.bookkeeper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class SearchViewModel(application: Application): AndroidViewModel(application){

    private val bookRepository = BookRepository(application)
    val allBooks: LiveData<List<Book>>

    init {
        allBooks = bookRepository.allBooks
    }

    fun update(book: Book){
            bookRepository.update(book)
    }

    fun delete(book: Book){
            bookRepository.delete(book)
    }

    fun getBooksByAuthorOrBook(searchString: String): LiveData<List<Book>> {
        return bookRepository.getBooksByAuthorOrBook(searchString)
    }
}