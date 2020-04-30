package com.example.gameapptask

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class QueryFunctions(private val context: Context) {
    var db: SQLFunctions? = null
    private var context1: Context? = null
    var flag = false
    var sql = ""
    var cursor: Cursor? = null
    var mp: MutableMap<String, String> = HashMap()
    var date = Date()
    var DateFormat = SimpleDateFormat("dd/MM/yyyy")
    var timeMatch = SimpleDateFormat("HH:mm a")
    var curDate=""
    var curTime=""
    val tableName = "AddData"

    //Create new Table
    open fun QueryFunctions(c: Context): Unit {
        context1 = c
    }

    fun CreateTable(): Boolean {
        db = SQLFunctions(context)
        if (!TableExists(tableName)) {
            sql = "create table '" + tableName + "' (" +
                    "reg_id Integer PRIMARY KEY AUTOINCREMENT," +
                    "name text, bestplayer text, colors text, " +
                    "date text, time text, count Integer)"
            println("SQL $sql")
            db!!.createTable(sql)
        }
        return flag
    }


    //Check table exists or no
    private fun TableExists(tableName: String): Boolean {
        db = SQLFunctions(context)
        sql =
            "select DISTINCT '$tableName' from sqlite_master where tbl_name ='$tableName'"
        println("Query $sql")
        flag = db!!.CheckTableExists(sql)
        return flag
    }


    //Insert new Register Users
    fun insertData(mp: Map<String?, String?>): Boolean {
        Log.d("Mapping Value", ""+mp)
        curDate= DateFormat.format(date)
        curTime =timeMatch.format(date)
        db = SQLFunctions(context)
        /*val values = ContentValues()*/
        val cnt= selectCountId(""+mp["name"]);
       /* values.put("name", mp["name"])
        values.put("bestplayer", mp["bestplayer"])
        values.put("colors", mp["color"])
        values.put("date", curDate)
        values.put("time", curTime)
        values.put("count",cnt)*/
        sql="insert into '$tableName' (name, bestplayer, colors, date, time, count) values('"+mp["name"]+"','"+mp["bestplayer"]+"'," +
                "'"+mp["color"]+"','"+curDate+"','"+curTime+"','"+cnt+"')"
        println("Query insertData $sql")
        val l: Boolean= db!!.insertNew(sql)
        println("Long Value $l")
        return l
    }



    fun fetchUserRegisterDetails(): ArrayList<Map<String, String>> {
        var list = arrayListOf<Map<String, String>>()
        db = SQLFunctions(context)
        sql = "select * from '$tableName'"
        println("Query : $sql")
        cursor = db!!.select(sql)
        if (cursor!!.moveToFirst()) {
            while (!cursor!!.isAfterLast) {
                var mp: MutableMap<String, String> = HashMap()
                mp["name"] = cursor!!.getString(cursor!!.getColumnIndex("name"))
                mp["bestplayer"] = cursor!!.getString(cursor!!.getColumnIndex("bestplayer"))
                mp["colors"] = cursor!!.getString(cursor!!.getColumnIndex("colors"))
                mp["date"] = cursor!!.getString(cursor!!.getColumnIndex("date"))
                mp["time"] = cursor!!.getString(cursor!!.getColumnIndex("time"))
                mp["count"] = cursor!!.getString(cursor!!.getColumnIndex("count"))
                list.add(mp);
                cursor!!.moveToNext()
            }
        }
        println("Mapping Fetch $list")
        cursor!!.close()
        return list
    }

    fun selectCountId(userName: String): Int? {
        db = SQLFunctions(context)
        var count1 = ""
        sql = "select count from '$tableName' where reg_id=(select max(reg_id) from '$tableName' where lower(name)='${userName.toLowerCase()}')"
        cursor = db!!.select(sql)
        if(cursor!=null && cursor!!.count!=0) {
            if (cursor!!.moveToFirst()) {
                while (!cursor!!.isAfterLast) {
                    println("Query : ${cursor!!.getString(cursor!!.getColumnIndex("count"))}")
                    count1 = cursor!!.getString(cursor!!.getColumnIndex("count"))
                    cursor!!.moveToNext()
                }
            }
        }
        cursor!!.close()
        println("Count $count1")
        var Numb=0
        try {
            if(count1.equals("") || count1.equals(null)) {
                Numb = 1
            }else{
                Numb = count1.toInt()+1
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
        return Numb
    }

}