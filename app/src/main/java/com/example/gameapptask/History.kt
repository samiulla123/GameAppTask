package com.example.gameapptask

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class History : AppCompatActivity() {
    var list= arrayListOf<Map<String, String>>()
    var sqlFunc: QueryFunctions = QueryFunctions(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setTitle("History")
        //Fetch All User Details
        list = sqlFunc.fetchUserRegisterDetails()
        println("List ${list}")
        displayData(list)
    }

    private fun displayData(list: ArrayList<Map<String, String>>) {
        println("Lis Value : ${list}")
        var linearLayout : LinearLayout = findViewById(R.id.line)
        for (x in 0 until list.size){
            val linear=LinearLayout(this)
            linear.setBackgroundResource(R.drawable.linedraw)
            val tableLayout : TableLayout = TableLayout(this);
            val date = TableRow(this)
            val name = TableRow(this)
            val msg1 = TableRow(this)
            val msg2 = TableRow(this)
            val crplyr= TableRow(this)
            val colorSel = TableRow(this)
            val ar = list[x] as Map<String, String>

            val dt = TextView(this)
            dt.gravity = Gravity.LEFT
            dt.text = "Game "+ar["count"]+" Date : "+ar["date"] +" Time : "+ ar["time"]
            date.addView(dt)
            tableLayout.addView(date)

            val Name = TextView(this)
            Name.text="Name : "+ar["name"]
            Name.gravity = Gravity.LEFT
            name.addView(Name)
            tableLayout.addView(name)

            val txtMsg = TextView(this)
            txtMsg.setTypeface(null, Typeface.BOLD)
            txtMsg.gravity = Gravity.LEFT
            txtMsg.text = "Who is the best cricketer in the world?"
            msg1.addView(txtMsg)
            tableLayout.addView(msg1)

            val player = TextView(this)
            player.gravity = Gravity.LEFT
            player.text = "Answer : "+ar["bestplayer"]
            crplyr.addView(player)
            tableLayout.addView(crplyr)

            val txtMsg1 = TextView(this)
            txtMsg1.gravity = Gravity.LEFT
            txtMsg1.setTypeface(null, Typeface.BOLD)
            txtMsg1.text = "What are the colors in the national flag?"
            msg2.addView(txtMsg1)
            tableLayout.addView(msg2)

            val color = TextView(this)
            color.gravity = Gravity.LEFT
            color.text = "Answer : "+ar["colors"]
            colorSel.addView(color)
            tableLayout.addView(colorSel)

            linear.addView(tableLayout)
            linearLayout.addView(linear)
        }
    }

}
