package com.eb.mydictionaryApp.ui

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.eb.mydictionaryApp.R


class MyListAdapter(private val context: Activity, private val id: Array<String>, private val word: Array<String>, private val meaning: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, word) {

    //initialize database handler class of sqlite database
    val db = DataBaseHandler(context)

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        //show design for List
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        //initialize textView
        val txtWord = rowView.findViewById(R.id.txt_word) as TextView
        val txtMeaning = rowView.findViewById(R.id.txt_meaning) as TextView
        val linearRow= rowView.findViewById<LinearLayout>(R.id.linear_row)

        //apply click on one row of data
        linearRow.setOnClickListener {
            //pass data from that row to show dialog function for EditValues
            showDialog(id[position],word[position],meaning[position]) }

        //show word on list
        txtWord.text =word[position]

        //show meaning on list
        txtMeaning.text = meaning[position]
        return rowView }
    private fun showDialog(id: String, word: String, meaning: String) {

        //initialize alert dialog

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null)
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)

        val edtMeaning = mDialogView.findViewById(R.id.edt_meaning) as EditText
        edtMeaning.setText(meaning)
        val edtWords = mDialogView.findViewById(R.id.edt_word) as EditText
        val mAlertDialog = mBuilder.show()
        edtWords.setText(word)

        val txtTitle =mDialogView.findViewById<TextView>(R.id.dialog_title)
        txtTitle.text = "Update Word"
        val add = mDialogView.findViewById(R.id.btn_addWord) as Button
        add.setOnClickListener {

            val word = edtWords.text.toString()
            val meaning = edtMeaning.text.toString()
            if (word == "") {
                edtWords.error = "Please enter word" }
            else if (meaning.equals("")) {
                edtMeaning.error = "Please enter meaning" }
            else {

                val myId:Int= Integer.valueOf(id)
                //update data in data base
                db.updateWords(WordsModel(myId,word,meaning))

                //refresh listView
                context.recreate()

                mAlertDialog.dismiss() }
        }
    }
}