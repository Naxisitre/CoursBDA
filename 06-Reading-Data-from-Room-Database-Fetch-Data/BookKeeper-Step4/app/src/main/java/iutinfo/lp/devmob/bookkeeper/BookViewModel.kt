package iutinfo.lp.devmob.bookkeeper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class BookViewModel(application: Application): AndroidViewModel(application){

    private val bookDao: BookDao
    val allBooks: LiveData<List<Book>>

    private val myExecutor = Executors.newSingleThreadExecutor()

    init {
        //bookDB est un thread
        val bookDB = BookRoomDatabase.getDatabase(application)
        bookDao = bookDB!!.bookDao()
        allBooks = bookDao.getAllBook() // ici car le bookViewModel doit avoir la liste des objets completes et actuel
    }

    fun insert(book: Book){
        myExecutor.execute{
            bookDao.insert(book)
        }
    }
}