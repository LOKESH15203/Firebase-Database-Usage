package com.example.firebasedatabaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eName = findViewById<EditText>(R.id.name)
        val eUsername = findViewById<EditText>(R.id.username)
        val eEmail = findViewById<EditText>(R.id.email)
        val eUserPassword = findViewById<EditText>(R.id.password)
        val eButton = findViewById<Button>(R.id.button)

        eButton.setOnClickListener{
            val name = eName.text.toString()
            val mail = eEmail.text.toString()
            val uniqueId = eUsername.text.toString()
            val password = eUserPassword.text.toString()

            val user = User(name, mail, password, uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener{
                eName.text.clear()
                eUsername.text.clear()
                eEmail.text.clear()
                eUserPassword.text.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}