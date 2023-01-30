package iutinfo.lp.devmob.bookkeeper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class BookRepository(application: Application) {
    private val bookDao: BookDao
    val allBooks: LiveData<List<Book>>

    private val myExecutor = Executors.newSingleThreadExecutor()

    init {
        val bookDB = BookRoomDatabase.getDatabase(application)
        bookDao = bookDB!!.bookDao()
        allBooks = bookDao.getAllBooks()
    }

    fun insert(book: Book) {
        myExecutor.execute{
            bookDao.insert(book)
        }
    }

    fun update(book: Book) {
        myExecutor.execute{
            bookDao.update(book)
        }
    }

    fun delete(book: Book) {
        myExecutor.execute{
            bookDao.delete(book)
        }
    }

    fun getBooksByAuthorOrBook(searchString: String): LiveData<List<Book>> {
        return bookDao.getBooksByAuhtorOrBook(searchString)
    }
}