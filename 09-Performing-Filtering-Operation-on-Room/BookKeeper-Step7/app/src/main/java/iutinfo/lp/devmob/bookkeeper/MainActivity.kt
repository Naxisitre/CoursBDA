package iutinfo.lp.devmob.bookkeeper

import android.app.Activity
import android.app.ProgressDialog.show
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.UUID

class MainActivity : AppCompatActivity(), BookListAdapter.OnDeleteClickListener {

    override fun onDeleteClickListener(book: Book) {
        bookViewModel.delete(book)
        Toast.makeText(applicationContext, R.string.deleted, Toast.LENGTH_LONG).show()
    }
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val bookDB = BookRoomDatabase.getDatabase(this);

        val bookListAdapter = BookListAdapter(this, this)
        recyclerview.adapter = bookListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent, NEW_BOOK_REQUEST_CODE)
        }

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        bookViewModel.allBooks.observe(this) { books ->
            books?.let {
                bookListAdapter.setBooks(books)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, intent)
        if(resultCode == Activity.RESULT_OK && requestCode == MainActivity.NEW_BOOK_REQUEST_CODE){
            insertNewBook(data)
        }
        else if (resultCode == Activity.RESULT_OK && requestCode == MainActivity.UPDATE_BOOK_REQUEST_CODE) {
            updatedBook(data)
            Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(applicationContext, R.string.not_saved, Toast.LENGTH_LONG).show()
        }
    }

    private fun insertNewBook(data: Intent?) {
        val id = UUID.randomUUID().toString()
        val authorName: String? = data?.getStringExtra(NewBookActivity.NEW_AUTHOR)
        val bookName: String? = data?.getStringExtra(NewBookActivity.NEW_BOOK)
        val book = Book(id, authorName!!, bookName!!)

        bookViewModel.insert(book)

        Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()
    }

    private fun updatedBook(data:Intent?){
        val id: String? = data?.getStringExtra(EditBookActivity.ID)
        val author: String? = data?.getStringExtra(EditBookActivity.UPDATED_AUTHOR)
        val book: String? = data?.getStringExtra(EditBookActivity.UPDATED_BOOK)
        val update_book = Book(id!!, author!!, book!!)
        bookViewModel.update(update_book)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        //Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        // Setting the SearchResultActivity to show the result
        val componentName = ComponentName(this, SearchResultActivity::class.java)
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView.setSearchableInfo(searchableInfo)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val ID = 3
        const val UPDATE_BOOK_REQUEST_CODE = 2
        const val NEW_BOOK_REQUEST_CODE = 1
    }


}


