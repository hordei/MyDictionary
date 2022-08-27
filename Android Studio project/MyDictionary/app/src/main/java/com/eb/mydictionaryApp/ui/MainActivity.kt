package com.eb.mydictionaryApp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.eb.mydictionaryApp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    val mylist: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize ListView
        listView = findViewById<ListView>(R.id.language_list_view)

        //name of jason file which is in assets folder
        val jsonFileString = Utils.getJsonFromAssets(
            applicationContext
        )
        //make list from User class
        val listUserType:Type = object : TypeToken<List<User?>?>() {}.type

        //initialize Gson Class for decode json
        val gson = Gson()

        //assign json data to User
        val users: List<User> =gson.fromJson<List<User>>(jsonFileString, listUserType)

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
            val language = users[position].name;

            //initialize intent and give other activity name .. SelectYouLanguage activity
            intent = Intent(this, SelectYourLanguage::class.java)

            // put language name which user selected ... this language name will go with intent
            intent.putExtra("language", language)

            //launch activity
            startActivity(intent)

            //Destroy this activity after launch another
            finish()
            }
        }
}