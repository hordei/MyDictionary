package com.eb.mydictionaryApp.ui

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//Define name for Sqlite database
val DATABASENAME = "Languages"   //name of sqlite database
val TABLENAME = "allWords"
val COL_WORD = "word"          //column
val COL_MEANING="meaning"    //column
val COL_ID = "id"              //column
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //Query for create Table
        val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_WORD + " VARCHAR(256),"  + COL_MEANING + " VARCHAR(256));"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
         onCreate(db);
    }
    //Function for insertion data in sqlite
    fun insertData(wordModel: WordsModel) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_WORD, wordModel.word)
        contentValues.put(COL_MEANING, wordModel.meaning)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
        }
    }
    //method for retrieve data from database
    fun readData(): MutableList<WordsModel> {
        val list: MutableList<WordsModel> = ArrayList()
        val db = this.readableDatabase

        val result = db.rawQuery("select * from $TABLENAME" ,null)

        if (result.moveToFirst()) {
            do {
                val wordModel = WordsModel(0,"","")
                wordModel.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                wordModel.word = result.getString(result.getColumnIndex(COL_WORD))
                wordModel.meaning = result.getString(result.getColumnIndex(COL_MEANING))
                list.add(wordModel)
            }
            while (result.moveToNext())
        }
        return list
    }

    //method for clear all data in sqlite
    fun deleteAll(){
        val db = this.writableDatabase
        db.execSQL("delete from $TABLENAME"); }
    //for updating in database
    fun updateWords(word: WordsModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, word.id)
        contentValues.put(COL_WORD, word.word) // EmpModelClass Name
        contentValues.put(COL_MEANING,word.meaning ) // EmpModelClass Email
        // Updating Row
        val success = db.update(TABLENAME, contentValues,"id="+word.id,null)
         db.close() // Closing database connection
        return success
    }


}








