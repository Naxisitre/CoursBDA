package com.example.viewmodeldemo

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class DataGenerator : ViewModel(){
    private lateinit var myRandomNumber : String

    fun getNumber(): String{
        Log.i(TAG, "Get Number")
        if(!::myRandomNumber.isInitialized){
            this.createNumber()
        }
        return myRandomNumber
    }

    private fun createNumber(){
        Log.i(TAG, "Create new number")
        val random = Random()
        myRandomNumber = "Number" + (random.nextInt(10-1)+1)
    }

    override fun onCleared() {
        Log.i(TAG, "ViewModel destroyed")
    }
    companion object{
        private val TAG = DataGenerator::class.simpleName
    }
}