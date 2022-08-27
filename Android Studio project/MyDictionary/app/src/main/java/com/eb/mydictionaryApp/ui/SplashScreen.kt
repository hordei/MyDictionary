package com.eb.mydictionaryApp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eb.mydictionaryApp.R

class SplashScreen : AppCompatActivity() {

    private val sharedPrefFile = "languagePref"

    //Milli second for splash screen
    private val splashTimeOut = 4000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //initialize shared preference
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        //get id from shared preference
        val sharedIdValue = sharedPreferences.getInt("saved_id",0)

        Handler().postDelayed(
            {
                // check if id 1 then go to Dictionary screen directly...
                // if value 0 then go to select language screen.
                if(sharedIdValue==1){
                    val myLanguage = sharedPreferences.getString("myLanguage","")
                    val learnLanguage = sharedPreferences.getString("languageLearn","")
                    intent = Intent(this, DictionaryActivity::class.java)
                    intent.putExtra("mylanguage", myLanguage)
                    intent.putExtra("language_learn", learnLanguage)
                    startActivity(intent)
                    finish()

                }else{
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }, splashTimeOut)


    }
}