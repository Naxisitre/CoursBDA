package iutinfo.lp.devmob.bookkeeper

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val bookDB = BookRoomDatabase.getDatabase(this);

        val bookListAdapter = BookListAdapter(this)
        recyclerview.adapter = bookListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent, NEW_BOOK_REQUEST_CODE)
        }

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, intent)
        if(resultCode == Activity.RESULT_OK && requestCode == MainActivity.NEW_BOOK_REQUEST_CODE){
            insertNewBook(data)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val NEW_BOOK_REQUEST_CODE = 1
    }
}


