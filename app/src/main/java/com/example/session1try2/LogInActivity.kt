package com.example.session1try2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.session1try2.Connection.api
import com.example.session1try2.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.goToCreateAccount.setOnClickListener {
            startActivity(Intent(this@LogInActivity, SignUpActivity::class.java))
            finish()
        }
        binding.enter.setOnClickListener {
            val email = binding.mailInp.text.toString()
            val password = binding.passInp.text.toString()
            if (email == "" || password == "") {
                Toast.makeText(this@LogInActivity, "Заполните все поля!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!checkMail(email)) {
                Toast.makeText(this@LogInActivity, "Почта введена некорректно!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            api.signIn(ModelAuth(email, password)).push(object: OnGetData<ModelIdentity> {
                override fun onGet(data: ModelIdentity) {
                    SharedPref.saveMail(email, this@LogInActivity)
                    SharedPref.savePassword(password, this@LogInActivity)
                    startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                    finish()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@LogInActivity, error, Toast.LENGTH_LONG).show()
                }
            }, this)
        }

    }
    fun checkMail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}