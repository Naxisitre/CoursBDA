**IUT La Rochelle**
**Université de La Rochelle**
**LP DevMob**
****
**BD Android**
**L. Alexis**
****
**TP-01**
****

# Partie 1 :
Défini la version de kotlin dans build.gradle du module : 
````gradle
def kotlin_version = "1.5.30"
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"
````
## 2- Add Kotlin class DataGenerator
````kotlin
package com.example.viewmodeldemo

import android.provider.ContactsContract.Data
import android.util.Log
import java.util.*

class DataGenerator {
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

    companion object{
        private val TAG = DataGenerator::class.simpleName
    }
}
````
## 3-MainActivity
ajouter dans les plus plugins build.gradle du module 

````gradle
id 'kotlin-android-extensions'
````
Dans le MainActivity.kt ajouter cette import et dans la class MainActivity doit ressembler à ceci  :
````kotlin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var  data: DataGenerator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataGenerator()
        val myRandomNumber = data.getNumber()
        tvNumber.text = myRandomNumber
        Log.i(TAG, "Random Number Set")
    }

    companion object{
        private val TAG = "Demo:" + DataGenerator::class.java.simpleName
    }
}
````

# Partie 2 :

## 2-Transform DataGenerator to a ViewModel
````kotlin
class DataGenerator : ViewModel()
````
