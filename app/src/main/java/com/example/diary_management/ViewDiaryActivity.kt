package com.example.diary_managemen

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diary_management.databinding.ActivityViewDiaryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewDiaryBinding
    private lateinit var database: DatabaseReference
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().reference
        userId = FirebaseAuth.getInstance().currentUser?.uid.orEmpty()

        val diaryList = mutableListOf<String>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, diaryList)
        binding.lvDiaries.adapter = adapter

        database.child("diaryEntries").child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    diaryList.clear()
                    for (entry in snapshot.children) {
                        val title = entry.child("title").getValue(String::class.java)
                        title?.let { diaryList.add(it) }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ViewDiaryActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}


