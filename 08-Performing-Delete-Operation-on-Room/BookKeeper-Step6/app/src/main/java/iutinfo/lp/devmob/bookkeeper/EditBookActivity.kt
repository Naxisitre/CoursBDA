package iutinfo.lp.devmob.bookkeeper

import android.app.Activity
import android.content.Intent
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_book.*

class EditBookActivity : AppCompatActivity() {

    var id:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        val bundle:Bundle? = intent.extras
        bundle?.let{
            id = bundle.getString("id")
            val author: String? = bundle.getString("author")
            val book: String? = bundle.getString("book")

            etAuthorName.setText(author)
            etBookName.setText(book)
        }
        bCancel.setOnClickListener {
            finish()
        }
        bAdd.setOnClickListener {
            val updatedAuthor = etAuthorName.text.toString()
            val updatedBook = etBookName.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(ID, id)
            resultIntent.putExtra(UPDATED_AUTHOR, updatedAuthor)
            resultIntent.putExtra(UPDATED_BOOK, updatedBook)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
    companion object{
        const val UPDATED_AUTHOR: String = "updated_author"
        const val UPDATED_BOOK: String = "updated_book"
        const val ID: String = "updated_id"
    }
}