package com.idz.colman24class1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.temporal.TemporalField

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.add_student_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_cancel_button)
        val nameTextField: EditText = findViewById(R.id.add_student_name_text_field)
        val idTextField: EditText = findViewById(R.id.add_student_id_text_field)
        val savedTextField: TextView = findViewById(R.id.add_student_success_saved_text_view)

        saveButton.setOnClickListener {
            savedTextField.text = "${nameTextField.text} ${idTextField.text} is saved...!!!"
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}