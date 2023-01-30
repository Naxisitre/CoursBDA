package iutinfo.lp.devmob.bookkeeper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {

    @Insert
    fun insert (book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("select * from book")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("select * from book where lower(author) like '%' || :searchString  || '%' or " +
            "lower(book) like '%' ||  :searchString || '%' ")
    fun getBooksByAuhtorOrBook(searchString: String): LiveData<List<Book>>
}