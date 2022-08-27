package com.eb.mydictionaryApp.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.eb.mydictionaryApp.R
import com.github.zawadz88.materialpopupmenu.popupMenu

import com.google.android.material.floatingactionbutton.FloatingActionButton


class DictionaryActivity : AppCompatActivity() {

    // Define variables
    lateinit var languageToLearn: String
    lateinit var myLanguage: String
    var recordSize:Int=0
    private lateinit var listView: ListView
    private lateinit var txtNoRecord:TextView
    val context = this

    //initialize database handler class of sqlite database
    val dataBase = DataBaseHandler(context)

    private val sharedPrefFile = "languagePref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_acttivity)


        //get language name from previous Activity
        languageToLearn = intent.getStringExtra("language_learn").toString()

        //get language name from previous Activity
        myLanguage = intent.getStringExtra("mylanguage").toString()

        //initialize textView of Toolbar in DictionaryActivity
        val txtScreenName = findViewById<TextView>(R.id.txtScreenName)

        //show language name on DictionaryActivity.. Top
        txtScreenName.setText(languageToLearn)

        //initialize ListView
        listView = findViewById<ListView>(R.id.words_list_view)

        //initialize textView (this textView will be visible when list have no data)
        txtNoRecord=findViewById(R.id.txt_noRecord)

        //View Record Function called from here
         viewRecord()

        //initialize Float action button-- on Which user click for addding words
        val addButton = findViewById<FloatingActionButton>(R.id.floating_add)

        //apply click listener on float action button
        addButton.setOnClickListener {

            //Show dialog for adding word and meaning
            showDialog()
        }

    }

    private fun showDialog() {

        //put  custom layout view in dialog
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)

        //build alert dialog
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)

        //show alert dialog
        val mAlertDialog = mBuilder.show()

        //initialize EditText in Dialog for enter Word
        val edtWords = mDialogView.findViewById(R.id.edt_word) as EditText

        //initialize EditText in Dialog for enter meaning
        val edtMeaning = mDialogView.findViewById(R.id.edt_meaning) as EditText

        //initialize button in dialog for submit the word and meaning.
        val add = mDialogView.findViewById(R.id.btn_addWord) as Button

        //apply click on add Button in alert dialog
        add.setOnClickListener {
            //get word from EditText and assign to word variable
            val word = edtWords.text.toString()

            //get word from EditText and assign to meaning variable
            val meaning = edtMeaning.text.toString()

            //check validation id editText not empty
            if (word == "") {
                //show error when word EditText empty
                edtWords.error = "Please enter word"
            } else if (meaning == "") {
                //show error when meaning EditText empty
                edtMeaning.error = "Please enter meaning"
            }
            else {
                //when both EditText not empty then call saveRecord function and pass both word and meaning to save record function
                saveRecord(word,meaning)

                // Disappear  dialog
                mAlertDialog.dismiss()
            }
        }


    }

    fun viewRecord() {
        //get data from database and put in list of WordList
        val wordList: List<WordsModel> = dataBase.readData()

        //define array for id
        val empArrayId = Array<String>(wordList.size) { "0" }

        //define array for word
        val arrayWord = Array<String>(wordList.size) { "null" }

        //define array for meaning
        val arrayMeaning = Array<String>(wordList.size) { "null" }

        var index = 0
        for (e in wordList) {
            //put all ids in array of id
            empArrayId[index] = e.id.toString()

            //put all ids in array of word
            arrayWord[index] = e.word

            //put all meaning in array of meanings
            arrayMeaning[index] = e.meaning
            index++
        }
        //get size of id array
        recordSize=empArrayId.size
        if(empArrayId.isEmpty()){
            //if array have no words then show TextView( No Record) on center screen
            txtNoRecord.isVisible=true
        }else{
            //if array have data then invisible textView of No Record
            txtNoRecord.isVisible=false

            //pass all array to MyListAdapter class
            val myListAdapter = MyListAdapter(this,empArrayId,arrayWord,arrayMeaning)
            listView.adapter = myListAdapter
        }



    }




    fun saveRecord(word: String, meaning: String) {
        //save record function from DatbaseHandler Class// pass data to that function
        val status = dataBase.insertData(WordsModel(0,word,meaning))
        viewRecord()
    }

    fun btn_menu(view: View) {
        //Top popUp menu for select anther language and Test
        val popupMenu = popupMenu {
            section {
                item {
                    label = "Test your knowledge"
                    icon = R.drawable.ic_test
                    callback = {
                        if(recordSize <5){
                            Toast.makeText(applicationContext,"Add minimum 5 words with meanings for Quiz",Toast.LENGTH_SHORT).show()
                        }else{
                            //go to Quiz Screen with learn language name.. when user click on Test You knowledge
                            intent = Intent(applicationContext, QuizScreen::class.java)
                            intent.putExtra("language_learn",languageToLearn)
                            startActivity(intent) }
                    }
                }
                item {
                    label = "Change Language"
                    icon = R.drawable.ic_languages
                    callback = {
                        //function for change language
                        changeLanguageDialoge() }
                }
            }
        }

        popupMenu.show(applicationContext, view) }
    fun changeLanguageDialoge(){

        //Alert dialog when user click on change language
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Do you want to learn another language? \n Existing words will be cleared.")
            .setCancelable(false)
            .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                //function when user click on proceed in alert dialog
                    dialog, id ->clearedData()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Alert")
        alert.show()
    }
    fun clearedData(){
        //Clear language values in shared preferences
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        //function for clear all words and meaning in sqlite database..
        dataBase.deleteAll()
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        //launch main activity Screen for select another language
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}