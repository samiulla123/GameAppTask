package com.example.gameapptask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class PageOne : AppCompatActivity() {
    var uname="";
    var bestPlayer="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_one)

        setTitle("Question 1")
        uname =intent.getStringExtra("UName");

        Toast.makeText(applicationContext, "Passed Name "+uname, Toast.LENGTH_LONG).show();

        //Select best player name
        var radio_group: RadioGroup=findViewById(R.id.rg)
        //Select Default selected player name
        var radio1: RadioButton = findViewById(radio_group.checkedRadioButtonId);
        bestPlayer=""+radio1.text;

        //Fetch user selected name
        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change :"+
                        " ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                bestPlayer=""+radio.text
            })
    }

    public fun submit(v : View?) : Unit{
        Log.d("UserName ", ""+uname)
        Log.d("Player", ""+bestPlayer)

        //Calling second question
        intent = Intent(applicationContext, SecondPage::class.java);
        intent.putExtra("UName",""+uname)
        intent.putExtra("player",""+bestPlayer)
        startActivity(intent)
    }
}
