package com.example.viewmodeldemo

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DataGenerator : ViewModel(){
    private lateinit var myRandomNumber : MutableLiveData<String>

    fun getNumber(): MutableLiveData<String> {
        Log.i(TAG, "Get Number")
        if(!::myRandomNumber.isInitialized){
            myRandomNumber = MutableLiveData()
            this.createNumber()
        }
        return myRandomNumber
    }

    fun createNumber(){
        Log.i(TAG, "Create new number")
        val random = Random()
        myRandomNumber.value = "Number" + (random.nextInt(10-1)+1)
    }

    override fun onCleared() {
        Log.i(TAG, "ViewModel destroyed")
    }
    companion object{
        private val TAG = DataGenerator::class.simpleName
    }
}