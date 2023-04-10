package com.example.session1try2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.session1try2.Connection.api

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (SharedPref.checkNotFistEnter(this@SplashActivity)) {
            SharedPref.saveNotFirstEnter(this@SplashActivity)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
            }, 1500)
        }else {
            api.signIn(ModelAuth(SharedPref.getMail(this)!!, SharedPref.getPassword(this)!!))
                .push(object: OnGetData<ModelIdentity> {
                    override fun onGet(data: ModelIdentity) {
                        Toast.makeText(this@SplashActivity, "Вход по сохраненным данным", Toast.LENGTH_LONG).show()
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