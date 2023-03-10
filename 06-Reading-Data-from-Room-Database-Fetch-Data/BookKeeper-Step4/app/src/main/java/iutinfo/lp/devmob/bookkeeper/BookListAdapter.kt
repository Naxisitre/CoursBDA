package iutinfo.lp.devmob.bookkeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class BookListAdapter(private val context : Context): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    var bookList: List<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater
            .from(context)
            .inflate(R.layout.list_item, parent,  false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book.author, book.book, position)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setBooks(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var pos:Int=0

        fun setData(author: String, book: String, position: Int){
            itemView.tvAuthor.text = author
            itemView.tvBook.text = book
            this.pos = position
        }

    }
}