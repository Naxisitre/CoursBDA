package iutinfo.lp.devmob.bookkeeper

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BookDao {
    //écrit l'ordre sql dans le book
    @Insert
    fun insert(book: Book)
}