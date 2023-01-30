package iutinfo.lp.devmob.bookkeeper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookRoomDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var bookRoomInstance: BookRoomDatabase? = null
        fun getDatabase(context: Context): BookRoomDatabase? {
            if (bookRoomInstance == null) {
                synchronized(BookRoomDatabase::class.java) {
                    if (bookRoomInstance == null) run {
                        bookRoomInstance = Room.databaseBuilder<BookRoomDatabase>(
                            context.applicationContext,
                            BookRoomDatabase::class.java,
                            "book_room_db.sqlite")
                            .build()
                    }
                }
            }
            return  bookRoomInstance!!
        }
    }
}