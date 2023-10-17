package com.example.loginpage_room_database

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginpage_room_database.databinding.ActivityLoginPageBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginPageBinding
    private lateinit var appDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            readData()
        }
    }

    private fun readData() {
        val username = binding.userName.text.toString()
        val password = binding.password.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            var user: userClass? = null
            GlobalScope.launch {
                user = appDb.userDao().findByUsername(username)
                if (user != null && user!!.password == password) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                } else {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(
                            applicationContext,
                            "Account does not exist !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}