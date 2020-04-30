package com.example.gameapptask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox

class SecondPage : AppCompatActivity() {
    var uname="";
    var bestPlayer="";
    var chk1=""; var chk2=""; var chk3=""; var chk4="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)
        setTitle("Question 2")


        uname =intent.getStringExtra("UName");
        bestPlayer = intent.getStringExtra("player");

    }

    //Check/UnCheck All CheckBox When user press on top right corner check box
    public fun CheckAll(v : View?) : Unit{
        var chk1 : CheckBox =(findViewById(R.id.chk1))
        var chk2 : CheckBox = findViewById(R.id.chk2)
        var chk3 : CheckBox =findViewById(R.id.chk3)
        var chk4 : CheckBox = findViewById(R.id.chk4)
        var chkall : CheckBox = findViewById(R.id.chkAll)
        if(chk1.isChecked || chk2.isChecked || chk3.isChecked || chk4.isChecked) {
            chk1.isChecked = false;
            chk2.isChecked = false;
            chk3.isChecked = false;
            chk4.isChecked = false;
            chkall.isChecked = false;
        }else{
            chk1.isChecked = true;
            chk2.isChecked = true;
            chk3.isChecked = true;
            chk4.isChecked = true;
            chkall.isChecked = true;
        }
    }

    //Submit for next step
    public fun Submit(v : View?) : Unit{
        var flagColor="";
        var chk1 : CheckBox =(findViewById(R.id.chk1))
        var chk2 : CheckBox = findViewById(R.id.chk2)
        var chk3 : CheckBox =findViewById(R.id.chk3)
        var chk4 : CheckBox = findViewById(R.id.chk4)

        //Fetch user input
        if(chk1.isChecked){
            flagColor+=""+chk1.text+",";
        }
        if(chk2.isChecked){
            flagColor+=""+chk2.text+",";
        }
        if(chk3.isChecked){
            flagColor+=""+chk3.text+",";
        }
        if(chk4.isChecked){
            flagColor+=""+chk4.text+",";
        }

        Log.d("ChechValue", flagColor);
        intent = Intent(applicationContext, SummaryReport::class.java);
        intent.putExtra("UName",""+uname)
        intent.putExtra("player",""+bestPlayer)
        intent.putExtra("colors",""+flagColor)
        startActivity(intent)
    }

}
