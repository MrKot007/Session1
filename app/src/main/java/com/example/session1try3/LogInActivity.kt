package com.example.session1try3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.session1try3.Connection.api
import com.example.session1try3.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.gotToCreateAccount.setOnClickListener {
            startActivity(Intent(this@LogInActivity, SignUpActivity::class.java))
        }
        binding.enter.setOnClickListener {
            val email = binding.mailInp.toString()
            val password = binding.passinp.toString()
            if (email == "" || password == "") {
                AlertDialog.Builder(this)
                    .setTitle("Заполните все поля!")
                    .setPositiveButton("Попробовать снова") {
                        dialog, id -> dialog.cancel()
                    }.create()
            }
            if (!checkMail(email)) {
                AlertDialog.Builder(this)
                    .setTitle("Почта введена некорректно!")
                    .setPositiveButton("Попробовать снова"){
                        dialog, id -> dialog.cancel()
                    }.create()
            }
            api.signIn(ModelAuth(email, password)).push(object: OnGetData<ModelIdentity>{
                override fun onGet(data: ModelIdentity) {
                    SharedPref.saveEmail(email, this@LogInActivity)
                    SharedPref.setPassword(password, this@LogInActivity)
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