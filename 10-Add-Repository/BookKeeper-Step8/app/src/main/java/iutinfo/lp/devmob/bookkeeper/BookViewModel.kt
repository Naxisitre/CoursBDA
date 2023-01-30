package iutinfo.lp.devmob.bookkeeper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class BookViewModel(application: Application): AndroidViewModel(application){

    private val bookRepository = BookRepository(application)
    val allBooks: LiveData<List<Book>>

    private val myExecutor = Executors.newSingleThreadExecutor()

    init {
        allBooks = bookRepository.allBooks
    }

    fun insert(book: Book){
        myExecutor.execute{
            bookRepository.insert(book)
        }
    }
    fun update(book: Book){
        myExecutor.execute{
            bookRepository.update(book)
        }
    }

    fun delete(book: Book){
        myExecutor.execute{
            bookRepository.delete(book)
        }
    }
}