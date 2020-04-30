package com.example.gameapptask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryReport : AppCompatActivity() {
    var uname="";
    var bestPlayer="";
    var color="";
    var sqlFunc: QueryFunctions = QueryFunctions(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_report)
        setTitle("Summary")

        uname =intent.getStringExtra("UName")
        bestPlayer = intent.getStringExtra("player")
        color = intent.getStringExtra("colors")

        Log.d("UserName ", ""+uname)
        Log.d("Player", ""+bestPlayer)
        Log.d("Colors", ""+color)

        //Display provided value by user as summary report

        var name : TextView = findViewById(R.id.name)
        name.setText(""+uname)
        var bestPlayerSel : TextView = findViewById(R.id.txtans1)
        bestPlayerSel.setText(""+bestPlayer)
        var colorPick : TextView = findViewById(R.id.colorans)
        colorPick.setText(""+color)

    }

    //Onclicking Finish Data will be store in sqlite and it will jump to first page
    public fun submit(v : View?) : Unit{
       val map : Map<String?, String?> = mapOf<String?, String?>("name" to uname, "bestplayer" to bestPlayer, "color" to color)
        sqlFunc!!.insertData(map)
        val intent = Intent(this, MainActivity::class.java)
        //Clear all previous history and destroy all activities
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}
