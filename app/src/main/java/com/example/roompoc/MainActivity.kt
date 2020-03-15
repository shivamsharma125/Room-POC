package com.example.roompoc

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var name : EditText? = null
    private var submit : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            Log.e(
                "TAG_ROOM_POC",
                "inside try MainActivity" + " withMigration = " + AppDatabase.withMigration
            )

            AppDatabase.getDatabase(this).getUserDao().getUserName()
        }
        catch (e : Exception) {
            AppDatabase.withMigration = false

            Log.e(
                "TAG_ROOM_POC",
                "inside catch MainActivity" + " withMigration = " + AppDatabase.withMigration
            )

            AppDatabase.getDatabase(this).getUserDao().getUserName()
            AppDatabase.withMigration = true
        }

        init()
        setListeners()

    }

    private fun init(){
        name = findViewById(R.id.editText)
        submit = findViewById(R.id.submit)
    }

    private fun setListeners() {
        submit?.setOnClickListener {
            saveDataAndOpenActivity()
        }
    }

    private fun saveDataAndOpenActivity() {

        val temp = name?.text?.trim().toString()
        val user = User()
        user.name = temp


        AppDatabase.getDatabase(applicationContext).getUserDao().insertUserName(user)


        if(temp.isNotEmpty()) {
            val intent = Intent(this,DisplayActivity::class.java)
            startActivity(intent)
        }

    }

}
