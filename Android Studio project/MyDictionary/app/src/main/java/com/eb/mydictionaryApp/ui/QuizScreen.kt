package com.eb.mydictionaryApp.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eb.mydictionaryApp.R
import java.util.*
import kotlin.collections.ArrayList

class QuizScreen : AppCompatActivity() {

    // define variables
    private lateinit var txtQuestion: TextView
    private lateinit var txtProgress: TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    lateinit var languageToLearn: String
    val context = this
    var recordSize: Int = 0
    var correct: Int = 0
    var wrong: Int = 0
    var questionIndex: Int = 0

    //initialize lists
    var questionsListGenerate: MutableList<Int> = ArrayList()
    var empArrayId: MutableList<String> = ArrayList()
    var arrayWord: MutableList<String> = ArrayList()
    var arrayMeaning: MutableList<String> = ArrayList()

    //initialize database handler class
    val db = DataBaseHandler(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_screen)

        txtQuestion = findViewById(R.id.txt_quiz_question)
        txtProgress = findViewById(R.id.txt_quizCounts)
        btn1 = findViewById(R.id.btnOpt1)
        btn2 = findViewById(R.id.btnOpt2)
        btn3 = findViewById(R.id.btnOpt3)

        //get Language from intent
        languageToLearn = intent.getStringExtra("language_learn").toString()

        //Function call for retrieve data from sqlite database
        viewRecord()

        // Function call for generate random numbers for quiz
        generateRandom()

        //Click on option one
        btn1.setOnClickListener {
            val selectedOption = btn1.text.toString().trim()
            showResult(selectedOption)
        }
        //click on option 2
        btn2.setOnClickListener {
            val selectedOption = btn2.text.toString().trim()
            showResult(selectedOption)
        }
        //click on option 3
        btn3.setOnClickListener {
            val selectedOption = btn3.text.toString().trim()
            showResult(selectedOption)
        }
    }
    // store result of single question in quiz
    fun showResult(selectedOption: String) {
        if (arrayMeaning[questionIndex-1] == selectedOption) {
            correct++
        } else {
            wrong++
        }
        //after store result again call generateRandom function for another question
        generateRandom()
    }

    fun btnBack(view: View) {
        //button back on top of quiz(arrow button)//  go back from quiz activity
        finish()
    }

    fun viewRecord() {
        //view record and store in lists

        val wordList: List<WordsModel> = db.readData()
        empArrayId = Array<String>(wordList.size) { "0" }.toMutableList()
        arrayWord = Array<String>(wordList.size) { "null" }.toMutableList()
        arrayMeaning = Array<String>(wordList.size) { "null" }.toMutableList()

        for ((index, e) in wordList.withIndex()) {
            empArrayId[index] = e.id.toString()
            arrayWord[index] = e.word
            arrayMeaning[index] = e.meaning

        }
        recordSize = empArrayId.size
    }

    //generate random options function
    fun generateRandom() {

        questionsListGenerate.clear()

        val number = IntArray(2)
        var count = 0
        var num: Int
        val r = Random()
        while (count < number.size) {
            num = r.nextInt(arrayWord.size)

            var repeat = false
            do {
                for (i in number.indices) {
                    if (num == number[i]) {
                         repeat = true
                        break
                    }else if(num == questionIndex){
                        repeat = true
                        break
                    }else if (i == count ) {
                        number[count] = num
                        count++
                        repeat = true
                        break
                    }

                }
            } while (!repeat)
        }
        for (j in number.indices) {
            print(number[j])
            // Log.e("xyz", number[j].toString())
            val genNum = number[j]

            questionsListGenerate.add(genNum)
        }
///////////////////////////////////////////////////////////////////////////////////

        questionsListGenerate.add(questionIndex)

        Collections.shuffle(questionsListGenerate)

///////////////////////////

        Log.e("abc", questionsListGenerate.toString())

        //Option button will be disabled when Quiz Completed
        if (questionIndex == recordSize) {
            showAlert()
            btn1.isEnabled = false
            btn2.isEnabled = false
            btn3.isEnabled = false
            return
        }

        //show progress on textView
        txtProgress.text = "Progress" + (questionIndex + 1) + "/" + (recordSize)
        txtQuestion.text = "What is the meaning of " + arrayWord[questionIndex] + " ?"

        //show options on Buttons
        btn1.text = arrayMeaning[questionsListGenerate[0]]
        btn2.text = arrayMeaning[questionsListGenerate[1]]
        btn3.text = arrayMeaning[questionsListGenerate[2]]
        questionIndex++
    }

    //alert dialog when quiz complete
    fun showAlert() {

        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("Total Words : $recordSize\n Your Score : $correct.")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                finish()
                //clearedData()
            })
        // negative button text and action


        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Result")
        // show alert dialog
        alert.show()
    }


}


