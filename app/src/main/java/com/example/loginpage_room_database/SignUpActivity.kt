package com.example.loginpage_room_database

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginpage_room_database.databinding.ActivitySignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var appDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)
        binding.btnSignup.setOnClickListener {
            writeData()

        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun writeData() {
        val userName = binding.userNameEditTxt.text.toString()
        val userPassword = binding.passwordEditTxt.text.toString()

        if (userName.isNotEmpty() && userPassword.isNotEmpty()) {
            val user = userClass(userName, userPassword)
            GlobalScope.launch(Dispatchers.IO) {
                appDb.userDao().insertOrUpdate(user, this@SignUpActivity)
            }
            binding.userNameEditTxt.text.clear()
            binding.passwordEditTxt.text.clear()

        } else {
            Toast.makeText(this@SignUpActivity, "Please fill the data", Toast.LENGTH_SHORT).show()

        }
    }
}


