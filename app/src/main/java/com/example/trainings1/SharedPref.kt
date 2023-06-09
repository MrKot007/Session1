package com.example.trainings1

import android.content.Context
import android.content.SharedPreferences

class SharedPref {
    companion object {
        fun saveNotFirstEnter(context: Context) {
            val sp: SharedPreferences = context.getSharedPreferences("enter", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putBoolean("isFirstEnter", false).apply()
        }
        fun checkNotFirstEnter(context: Context) : Boolean {
            val sp = context.getSharedPreferences("enter", Context.MODE_PRIVATE)
            return sp.getBoolean("isFirstEnter", true)
        }
        fun saveMail(email: String, context: Context) {
            val sp: SharedPreferences = context.getSharedPreferences("mail", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("email", email).apply()
        }
        fun getMail(context: Context): String? {
            val sp: SharedPreferences = context.getSharedPreferences("mail", Context.MODE_PRIVATE)
            return sp.getString("email", "no email had been set")
        }
        fun savePassword(password: String, context: Context) {
            val sp: SharedPreferences = context.getSharedPreferences("password", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("password", password).apply()
        }
        fun getPassword(context: Context): String? {
            val sp: SharedPreferences = context.getSharedPreferences("password", Context.MODE_PRIVATE)
            return sp.getString("password", "no password had been set")
        }
    }
}