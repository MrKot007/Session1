package com.example.trainings1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trainings1.Connection.api
import com.example.trainings1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            finish()
        }
        binding.register.setOnClickListener {
            val bio = listOf(
                binding.name.text.toString(),
                binding.lastname.text.toString(),
                binding.patronymic.text.toString(),
                binding.sex.text.toString(),
                binding.birth.text.toString(),
                binding.mail.text.toString(),
                binding.pass.text.toString(),
                binding.passRep.toString()
            )
            bio.forEach {
                if (it == "") {
                    Toast.makeText(this@SignUpActivity, "Заполните все поля!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
            if (bio[3] != "Мужчина" || bio[3] != "Женщина"){
                Toast.makeText(this@SignUpActivity, "Пол указан некорректно!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (bio[6] != bio[7]) {
                Toast.makeText(this@SignUpActivity, "Пароли не совпадают", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(bio[5]).matches()) {
                Toast.makeText(this@SignUpActivity, "Почта указана некорректно", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            api.signUp(ModelReg(bio[0], bio[1], bio[2], bio[5], bio[6], bio[4], bio[3])).push(object: OnGetData<ModelIdentity> {
                override fun onGet(data: ModelIdentity) {
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                    finish()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@SignUpActivity, error, Toast.LENGTH_LONG).show()
                }

            }, this)
        }
    }
}