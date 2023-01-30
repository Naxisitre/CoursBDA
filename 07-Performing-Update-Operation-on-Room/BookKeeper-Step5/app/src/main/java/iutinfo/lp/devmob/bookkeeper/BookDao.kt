package iutinfo.lp.devmob.bookkeeper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    //écrit l'ordre sql dans le book
    @Insert
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Query("select * from book")
    fun getAllBook(): LiveData<List<Book>>
}