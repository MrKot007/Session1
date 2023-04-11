package com.example.finalpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.finalpractice.Connection.api
import com.example.finalpractice.databinding.ActivityLogInBinding

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
            val email = binding.mail.text.toString()
            val password = binding.pass.text.toString()
            if (!validMail(email)) {
                AlertDialog.Builder(this)
                    .setTitle("Почта введена некорректно!")
                    .setPositiveButton("Попробовать снова") {
                        dialog, id -> dialog.cancel()
                    }.create().show()
                return@setOnClickListener
            }
            if (email == "" || password == "") {
                AlertDialog.Builder(this)
                    .setTitle("Заполните все поля!")
                    .setPositiveButton("Попробовать снова") {
                        dialog, id -> dialog.cancel()
                    }.create().show()
                return@setOnClickListener
            }
            api.signIn(ModelAuth(email, password)).push(object: OnGetData<ModelIdentity>{
                override fun onGet(data: ModelIdentity) {
                    SharedPref.saveEmail(email, this@LogInActivity)
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
    fun validMail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}