package com.example.roompoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {

    private var name : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        init()
        setListeners()

    }

    private fun init(){

        name = findViewById(R.id.textView)

        val user = AppDatabase.getDatabase(applicationContext).getUserDao().getUserName() as ArrayList<User>


        val mName = user.last().name

        if(!mName!!.isEmpty())
            name?.setText(mName)

    }

    private fun setListeners() {

        name?.setOnClickListener {

               val id = AppDatabase.getDatabase(applicationContext).getUserDao().updateUserName(name!!.text.toString())
                Log.e("TAG_ROOM_POC", id.toString())

            var user = ArrayList<User>()
            Thread(Runnable {
                user = AppDatabase.getDatabase(applicationContext).getUserDao().getUserName() as ArrayList<User>
            }).start()

            Thread.sleep(200)

            val mName = user.last().name

            if(!mName!!.isEmpty())
                name?.setText(mName)


        }

    }

}
