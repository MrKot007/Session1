package com.example.session1try2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.session1try2.Connection.api
import com.example.session1try2.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LogInActivity::class.java))
            finish()
        }
        binding.register.setOnClickListener {
            val bio = listOf(
                binding.name.text.toString(),
                binding.sname.text.toString(),
                binding.patronymic.text.toString(),
                binding.mail.text.toString(),
                binding.pass.text.toString(),
                binding.birth.text.toString(),
                binding.sex.text.toString(),
                binding.repPass.toString()
            )
            bio.forEach {
                if (it == "") {
                    Toast.makeText(this@SignUpActivity, "Заполните все поля!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(bio[3]).matches()) {
                Toast.makeText(this@SignUpActivity, "Почта введена некорректно!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (bio[7] != bio[4]) {
                Toast.makeText(this@SignUpActivity, "Пароли не совпадают!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            api.signUp(ModelReg(bio[0], bio[1], bio[2], bio[3], bio[4], bio[5], bio[6]))
                .push(object: OnGetData<ModelIdentity>{
                    override fun onGet(data: ModelIdentity) {
                        startActivity(Intent(this@SignUpActivity, LogInActivity::class.java))
                        Toast.makeText(this@SignUpActivity, "Аккаунт создан!", Toast.LENGTH_LONG).show()
                        finish()
                    }

                    override fun onError(error: String) {
                        Toast.makeText(this@SignUpActivity, error, Toast.LENGTH_LONG).show()
                    }
                }, this)
        }
    }
}