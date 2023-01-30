**IUT La Rochelle**
**Université de La Rochelle**
**LP DevMob**
****
**BD Android**
**L. Alexis**
****
**04-01-Working-with-Room**
****
```gradle
def lifecycle_version ="1.1.1"
    implementation "android.arch.lifecycle:extensions:$lifecycle_version"
    kapt "android.arch.lifecycle:compiler:$lifecycle_version"

    def room_version='1.1.1'
    implementation "android.arch.persistence.room:runtime:$room_version"
    kapt "android.arch.persistence.room:compiler:$room_version"
    androidTestImplementation "android.arch.persistence.room:testing:$room_version"
```
## 1-Création classe book
```kotlin
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="book")
class Book (
    @PrimaryKey
    val id:String,

    @ColumnInfo(name= "author")
    val author : String,

    val book : String
)
{}
```
## 2-Création interface BookDao
Le insert permet d'écrire l'ordre sql dans le book
```kotlin
@Dao
interface BookDao {
    
    @Insert
    fun insert(book: Book)
}
```
## 3-Création base de données pour le book
```kotlin
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
```