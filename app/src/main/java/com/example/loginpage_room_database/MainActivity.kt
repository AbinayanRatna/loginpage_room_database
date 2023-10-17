package com.example.loginpage_room_database

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.loginpage_room_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.add -> {
                    Toast.makeText(this@MainActivity,"clicked Add Button",Toast.LENGTH_SHORT).show()
                }

                R.id.home -> {
                    Toast.makeText(this@MainActivity,"clicked Home Button",Toast.LENGTH_SHORT).show()

                }

                R.id.viewPost -> {
                    Toast.makeText(this@MainActivity,"clicked view Button",Toast.LENGTH_SHORT).show()

                }

                R.id.logOut -> {
                    val builderExit =
                        AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
                            .setTitle("News App")
                            .setMessage("Do you want to Logout ?")
                            .setPositiveButton("Logout") { dialog: DialogInterface, which: Int ->
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .setNegativeButton("cancel", null)
                    val dialog1 = builderExit.create()
                    dialog1.setCancelable(false)
                    dialog1.setCanceledOnTouchOutside(false)
                    dialog1.show()
                    dialog1.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK)
                    dialog1.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK)

                }
            }
            true
        }
    }

    }
