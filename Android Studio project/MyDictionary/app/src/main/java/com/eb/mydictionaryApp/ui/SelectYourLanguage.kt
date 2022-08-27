package com.eb.mydictionaryApp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.eb.mydictionaryApp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SelectYourLanguage : AppCompatActivity() {

    private lateinit var listView: ListView
    val mylist: MutableList<String> = ArrayList()
    lateinit var learnLanguage: String

    private val sharedPrefFile = "languagePref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_your_language)

        //initialize list view
        listView = findViewById<ListView>(R.id.list_view_myLanguage)

        // get language name which is came with intent from previous activity(MainActivity)
        learnLanguage = intent.getStringExtra("language").toString()

        //initialize sharedPreference... to save value offline in app
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        //json file name from assets directory in project folder
        val jsonFileString = Utils.getJsonFromAssets(
            applicationContext
        )

        //initialize Gson to decode Json File
        val gson = Gson()

        //make list from User class
        val listUserType: Type = object : TypeToken<List<User?>?>() {}.type

        //assign json data to User
        val users: List<User> = gson.fromJson<List<User>>(jsonFileString, listUserType)

        // put json data in user list------  get Name of language from json and give it to my list
        for (i in users.indices) {
            //add language names to list
            mylist.add(users[i].name)
        }

        //put list in List View
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mylist)
        listView.adapter = adapter

        // apply click on List view.... when some one click on List view item
        listView.setOnItemClickListener { adapterView, view, position: Int, id: Long ->

            //get language name from list position where clicked... and assign to language variable
            val myLanguage = users[position].name;

            //check if user again select previous language
            if(myLanguage == learnLanguage){
                Toast.makeText(applicationContext,"Please select another language.",Toast.LENGTH_SHORT).show()
                return@setOnItemClickListener
            }
            //initialize intent and give other activity name .. SelectYouLanguage activity
            intent = Intent(this, DictionaryActivity::class.java)

            // put language name which user selected ... this language name will go with intent
            intent.putExtra("mylanguage", myLanguage)

            // put language name which user selected for Learn... this language name will go with intent
            intent.putExtra("language_learn", learnLanguage)

            // For Edit Shared Preference file to store values
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()

            //put id
            editor.putInt("saved_id",1)

            //put language--  own language
            editor.putString("myLanguage",myLanguage)

            //put language -- language for Learn
            editor.putString("languageLearn",learnLanguage)

            //save all above values
            editor.apply()
            editor.commit()

            //launch DictionaryActivity
            startActivity(intent)

            //destroy this activity after launch DictionaryActivity
            finish()
        }
    }
}