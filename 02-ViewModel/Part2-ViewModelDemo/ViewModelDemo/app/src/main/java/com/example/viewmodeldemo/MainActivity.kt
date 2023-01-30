package com.example.viewmodeldemo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    lateinit var  data: DataGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //data = DataGenerator()
        data = ViewModelProviders.of(this).get(DataGenerator::class.java)

        val myRandomNumber = data.getNumber()
        tvNumber.text = myRandomNumber
        Log.i(TAG, "Random Number Set")
    }

    companion object{
        private val TAG = "Demo:" + DataGenerator::class.java.simpleName
    }
}