package com.example.gameapptask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import java.util.*

class NamePage : AppCompatActivity() {
    var awesomeValidation: AwesomeValidation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_page)
        setTitle("User Name")


    }

    //Display Menu option on right top corner
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dropdownmenu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history -> {
                intent = Intent(applicationContext, History::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    public fun submit(v : View?) : Unit{

        //Fetch UserName And Calling Next Page
        var name=findViewById(R.id.Name) as EditText

        //Check EditText is empty
        if(name.text.length<=0){
            name.setError("Enter User Name")
        }else {
            intent = Intent(applicationContext, PageOne::class.java);
            intent.putExtra("UName", "" + name.text)
            startActivity(intent)
        }
    }
}
