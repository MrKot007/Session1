package com.example.trainings1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.trainings1.Connection.api

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (SharedPref.checkNotFirstEnter(this@MainActivity)) {
            SharedPref.saveNotFirstEnter(this)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@MainActivity, OnBoardingActivity::class.java))
                finish()
            }, 1500)
        }else {
            api.signIn(ModelAuth(
                SharedPref.getMail(this@MainActivity).toString(),
                SharedPref.getPassword(this@MainActivity).toString()
            )).push(object: OnGetData<ModelIdentity> {
                override fun onGet(data: ModelIdentity) {
                    Toast.makeText(this@MainActivity, "Вход по сохраненным данным", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MainActivity, LogOutActivity::class.java))
                    finish()
                }

                override fun onError(error: String) {
                    startActivity(Intent(this@MainActivity, OnBoardingActivity::class.java))
                    finish()
                }
            }, this)
        }


    }
}