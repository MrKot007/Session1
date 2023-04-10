package com.example.session1try3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.session1try3.Connection.api

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (SharedPref.checkNotFirstEnter(this@SplashActivity)) {
            SharedPref.saveNotFirstEnter(this@SplashActivity)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
                finish()
            }, 1500)
        }else {
            api.signIn(ModelAuth(SharedPref.getEmail(this@SplashActivity)!!,
                SharedPref.getPassword(this@SplashActivity)!!
            )).push(object: OnGetData<ModelIdentity> {
                override fun onGet(data: ModelIdentity) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

                override fun onError(error: String) {
                    startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
                    finish()
                }

            }, this)
        }

    }
}