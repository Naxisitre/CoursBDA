package iutinfo.lp.devmob.bookkeeper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "book")
class Book(@PrimaryKey
           val id: String,

           @ColumnInfo (name = "author")
           val author: String,
           val book: String,
           val description: String,

           @ColumnInfo(name = "last_updated")
           val lastUpdated: Date?
           )
{}