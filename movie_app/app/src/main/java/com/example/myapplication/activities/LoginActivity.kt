package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class LoginActivity : AppCompatActivity() {

    private lateinit var userEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var loginBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }
    
    private fun  initView() {
        userEdt = findViewById(R.id.editTextText)
        passwordEdt = findViewById(R.id.editTextPassword)
        loginBtn =  findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val user = userEdt.text.toString()
            val password = passwordEdt.text.toString()

            if (user.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill your username and password", Toast.LENGTH_SHORT).show()
            } else if (user.equals("test") && password.equals("test")) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "UserName or Password is not correct", Toast.LENGTH_SHORT).show()
            }
        }
    }
}