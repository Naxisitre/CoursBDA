package iutinfo.lp.devmob.bookkeeper

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Build.ID
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_book.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class SearchResultActivity : AppCompatActivity(), BookListAdapter.OnDeleteClickListener {

    private lateinit var searchViewModel: SearchViewModel
    private val TAG = this.javaClass.simpleName
    private var bookListAdapter: BookListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.visibility = View.INVISIBLE

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        bookListAdapter = BookListAdapter(this, this)
        recyclerview.adapter = bookListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        searchViewModel.allBooks.observe(this) { books ->
            books?.let {
                bookListAdapter!!.setBooks(books)
            }
        }
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent){
        if (Intent.ACTION_SEARCH == intent.action) {
            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
            Log.i(TAG, "Search Query: $searchQuery")
            searchViewModel.getBooksByAuthorOrBook("$searchQuery".lowercase()).observe(this
            ) { books ->
                books?.let { bookListAdapter!!.setBooks(books) }
            }
        }
    }

    private fun updateBook(data: Intent?) {
        val id: String? = data?.getStringExtra(EditBookActivity.ID)
        val author: String? = data?.getStringExtra(EditBookActivity.UPDATED_AUTHOR)
        val book: String? = data?.getStringExtra(EditBookActivity.UPDATED_BOOK)
        val description: String? = data?.getStringExtra(EditBookActivity.UPDATED_DESCRIPTION)
        val currentTime: Date? = Calendar.getInstance().time

        val updated_book = Book(id!!, author!!, book!!, description!!, currentTime!!)
        searchViewModel.update(updated_book)
    }

    override fun onDeleteClickListener(book: Book) {
        searchViewModel.delete(book)
        Toast.makeText(applicationContext, R.string.deleted, Toast.LENGTH_LONG).show()
    }
}