package com.example.trainings1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trainings1.Connection.api
import com.example.trainings1.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.goToCreateAccount.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
        binding.enter.setOnClickListener {
            val email = binding.nameInp.text.toString()
            val password = binding.parol.text.toString()
            if (password == "" || email == "") {
                Toast.makeText(this@SignInActivity, "Заполнены не все поля!", Toast.LENGTH_LONG).show()
            }
            if (!checkMail(email)) {
                Toast.makeText(this@SignInActivity, "Почта указана неверно!", Toast.LENGTH_LONG).show()
            }
            api.signIn(ModelAuth(email, password)).push(object: OnGetData<ModelIdentity>{
                override fun onGet(data: ModelIdentity) {
                    SharedPref.saveMail(email, this@SignInActivity)
                    SharedPref.savePassword(password, this@SignInActivity)
                    startActivity(Intent(this@SignInActivity, LogOutActivity::class.java))
                }

                override fun onError(error: String) {
                    Toast.makeText(this@SignInActivity, error, Toast.LENGTH_LONG).show()
                }
            }, this)
        }
    }
    fun checkMail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}