package iutinfo.lp.devmob.bookkeeper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.util.concurrent.Executors

class BookViewModel(application: Application): AndroidViewModel(application){

    private val bookDao: BookDao

    private val myExecutor = Executors.newSingleThreadExecutor()

    init {
        //bookDB est un thread
        val bookDB = BookRoomDatabase.getDatabase(application)
        bookDao = bookDB!!.bookDao()
    }

    fun insert(book: Book){
        myExecutor.execute{
            bookDao.insert(book)
        }
    }
}