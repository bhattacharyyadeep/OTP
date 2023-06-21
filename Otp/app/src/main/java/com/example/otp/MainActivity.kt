package com.example.otp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.otp.VerifyPhoneActivity
import com.google.firebase.FirebaseApp

import com.google.firebase.auth.FirebaseAuth
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var editTextCountryCode: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCountryCode = findViewById(R.id.editTextCountryCode)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonContinue = findViewById(R.id.buttonContinue)



        buttonContinue.setOnClickListener {
            val code = editTextCountryCode.text.toString().trim()
            val number = editTextPhone.text.toString().trim()

            if (number.isEmpty() || number.length < 10) {
                editTextPhone.error = "Valid number is required"
                editTextPhone.requestFocus()
                return@setOnClickListener
            }

            val phoneNumber = code + number

            val intent = Intent(this@MainActivity, VerifyPhoneActivity::class.java)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}
