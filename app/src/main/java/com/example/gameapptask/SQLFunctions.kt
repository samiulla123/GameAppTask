package com.example.gameapptask

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class SQLFunctions(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    var flag = false
    var count = 0
    var mp: Map<String, String> =
        HashMap()
    var finalCur: Cursor? = null
    var db = this.readableDatabase

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {}

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    fun CheckTableExists(sql: String?): Boolean {
        val cur = db.rawQuery(sql, null)
        println("Cursor $cur")
        if (cur.count > 0) {
            flag = true
        }
        cur.close()
        return flag
    }

    fun createTable(sql: String?): Boolean {
        db.execSQL(sql)
        return flag
    }

    fun Insert(tableName: String?, values: ContentValues?): Long {
        println("Query insert $values")
        return db.insert(tableName, null, values)
    }
    fun insertNew(sql: String?): Boolean {
        db.execSQL(sql)
        return true
        // return db.update(tableName, values,  FieldName+" = "+Id, null) > 0;
    }

    fun select(sql: String?): Cursor? {
        val date = ""
        val cursor = db.rawQuery(sql, null)
        finalCur = cursor
        println("Cursor Value " + cursor!!.count)
        return cursor
    }

    companion object {
        private const val DATABASE_NAME = "gameStore"
    }
}