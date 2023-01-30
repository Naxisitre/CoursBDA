package iutinfo.lp.devmob.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_new_book.*

class NewBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        tvLastUpdated.visibility = View.INVISIBLE

        bAdd.setOnClickListener {
            val resultIntent = Intent()
            if (TextUtils.isEmpty(etAuthorName.text) || TextUtils.isEmpty(etBookName.text)) {
                setResult(Activity.RESULT_CANCELED)
            }
            else {
                val authorName = etAuthorName.text.toString()
                val bookName = etBookName.text.toString()
                val description = etDescription.text.toString()

                resultIntent.putExtra(NEW_AUTHOR, authorName)
                resultIntent.putExtra(NEW_BOOK, bookName)
                resultIntent.putExtra(NEW_DESCRIPTION, description)

                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()
        }
        bCancel.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val NEW_AUTHOR: String = "new_author"
        const val NEW_BOOK: String = "new_book"
        const val NEW_DESCRIPTION: String = "new_description"
    }
}