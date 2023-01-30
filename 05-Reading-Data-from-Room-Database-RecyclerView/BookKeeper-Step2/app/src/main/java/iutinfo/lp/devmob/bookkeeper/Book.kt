package iutinfo.lp.devmob.bookkeeper

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
