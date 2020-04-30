package com.example.gameapptask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var sqlFunc : QueryFunctions =QueryFunctions(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sqlFunc!!.CreateTable()
      intent= Intent(applicationContext, NamePage::class.java)
        startActivity(intent)
        finish()
    }
}
