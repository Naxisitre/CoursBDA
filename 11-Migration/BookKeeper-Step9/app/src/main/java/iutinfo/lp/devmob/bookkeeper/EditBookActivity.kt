package iutinfo.lp.devmob.bookkeeper

import android.app.Activity
import android.content.Intent
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_book.*
import kotlinx.android.synthetic.main.activity_new_book.tvLastUpdated
import kotlinx.android.synthetic.main.list_item.*

class EditBookActivity : AppCompatActivity() {

    var id:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        val bundle:Bundle? = intent.extras
        bundle?.let {
            id = bundle.getString("id")
            val author: String? = bundle.getString("author")
            val book: String? = bundle.getString("book")
            val description: String? = bundle.getString("description")
            val lastUpdated: String? = bundle.getString("last_updated")

            etAuthorName.setText(author)
            etBookName.setText(book)
            etDescription.setText(description)
            tvLastUpdated.text = lastUpdated
        }

        bAdd.setOnClickListener {
            val updatedAuthor = etAuthorName.text.toString()
            val updatedBook = etBookName.text.toString()
            val updatedDescription = etDescription.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(ID, id)
            resultIntent.putExtra(UPDATED_AUTHOR, updatedAuthor)
            resultIntent.putExtra(UPDATED_BOOK, updatedBook)
            resultIntent.putExtra(UPDATED_DESCRIPTION, updatedDescription)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }
    }
    companion object {
        const val ID = "id_updt"
        const val UPDATED_AUTHOR = "author_name"
        const val UPDATED_BOOK = "book_name"
        const val UPDATED_DESCRIPTION = "book_description"
     }
}