package com.example.diary_management

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diary_managemen.AddDiaryActivity
import com.example.diary_managemen.LoginActivity
import com.example.diary_managemen.ViewDiaryActivity
import com.example.diary_management.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this) // Ensure Firebase is initialized

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase Authentication instance
        auth = FirebaseAuth.getInstance()

        // Button click listeners
        binding.btnAddDiary.setOnClickListener {
            startActivity(Intent(this, AddDiaryActivity::class.java))
        }

        binding.btnViewDiary.setOnClickListener {
            startActivity(Intent(this, ViewDiaryActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
