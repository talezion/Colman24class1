package com.idz.colman24class1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: 1. Button to navigate to Add Student ✅
        // TODO: 2. Create a button listener
        // TODO: 3. Create an intent

        val addStudentButton: Button = findViewById(R.id.main_activity_add_student_button)

//        class MyListener: View.OnClickListener {
//            override fun onClick(v: View?) {
//                TODO("Not yet implemented")
//            }
//        }
//
//        val listener = MyListener()
//        addStudentButton.setOnClickListener(listener)

//        addStudentButton.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                TODO("Not yet implemented")
//            }
//        })

        addStudentButton.setOnClickListener {

            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
}