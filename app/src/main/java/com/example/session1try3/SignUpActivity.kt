package com.example.session1try3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.session1try3.Connection.api
import com.example.session1try3.databinding.ActivitySignUpBinding

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
                binding.sname.text.toString(),
                binding.name.text.toString(),
                binding.patronymic.text.toString(),
                binding.sex.text.toString(),
                binding.date.text.toString(),
                binding.mail.text.toString(),
                binding.pass.text.toString(),
                binding.passRep.toString()
            )
            bio.forEach {
                if (it == "") {
                    AlertDialog.Builder(this)
                        .setTitle("Заполните все поля!")
                        .setPositiveButton("Попробовать снова") {
                            dialog, id -> dialog.cancel()
                        }.create()
                }
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(bio[5]).matches()) {
                AlertDialog.Builder(this)
                    .setTitle("Почта введена некорректно!")
                    .setPositiveButton("Попробовать снова") {
                        dialog, id -> dialog.cancel()
                    }.create()
            }
            api.signUp(ModelReg(bio[1], bio[0], bio[2], bio[5], bio[6], bio[4], bio[3])).push(object: OnGetData<ModelIdentity>{
                override fun onGet(data: ModelIdentity) {
                    Toast.makeText(this@SignUpActivity, "Аккаунт успепшно создан", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@SignUpActivity, LogInActivity::class.java))
                    finish()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@SignUpActivity, error, Toast.LENGTH_LONG).show()
                }

            }, this)
        }
    }
}