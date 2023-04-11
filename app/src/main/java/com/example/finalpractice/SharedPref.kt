package com.example.finalpractice

import android.content.Context
import android.content.SharedPreferences

class SharedPref {
    companion object {

        fun saveNotFirstEnter(context: Context) {
            val sp: SharedPreferences = context.getSharedPreferences("enter", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putBoolean("isFirstEnter", false).apply()
        }
        fun checkNotFirstEnter(context: Context): Boolean {
            val sp: SharedPreferences = context.getSharedPreferences("enter", Context.MODE_PRIVATE)
            return sp.getBoolean("isFirstEnter", true)
        }
        fun saveEmail() {}
    }
}