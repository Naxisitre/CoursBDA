package iutinfo.lp.devmob.bookkeeper

import android.app.Activity
import android.app.ProgressDialog.show
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import iutinfo.lp.devmob.bookkeeper.MainActivity.Companion.NEW_BOOK_REQUEST_CODE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.UUID

class SearchResultActivity : AppCompatActivity(), BookListAdapter.OnDeleteClickListener {
    private lateinit var bookListAdapter: BookListAdapter
    override fun onDeleteClickListener(book: Book) {
        searchViewModel.delete(book)
        Toast.makeText(applicationContext, R.string.deleted, Toast.LENGTH_LONG).show()
    }
    private lateinit var searchViewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.visibility = View.INVISIBLE

        val bookDB = BookRoomDatabase.getDatabase(this);

        bookListAdapter = BookListAdapter(this, this)
        recyclerview.adapter = bookListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent, NEW_BOOK_REQUEST_CODE)
        }

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        searchViewModel.allBooks.observe(this) { books ->
            books?.let {
                bookListAdapter.setBooks(books)
            }
        }
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if(Intent.ACTION_SEARCH == intent.action){
            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
            Log.i(TAG, "Search Query: $searchQuery")
            searchViewModel.getBooksByAuthorOrBook("$searchQuery".lowercase()).observe(
                this){ books ->
                books?.let{ bookListAdapter!!.setBooks(books) }
            }
        }
    }

    companion object{
        const val TAG = "4"
        const val ID = 3
        const val UPDATE_BOOK_REQUEST_CODE = 2
        const val NEW_BOOK_REQUEST_CODE = 1
    }


}


