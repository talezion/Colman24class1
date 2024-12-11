package com.idz.colman24class1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.colman24class1.adapter.StudentsAdapter
import com.idz.colman24class1.model.Model
import com.idz.colman24class1.model.Student

class StudentsListViewActivity : AppCompatActivity() {

    var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: 1. Create StudentsListViewActivity
        // TODO: 2. Set LAUNCHER
        // TODO: 3. Set layout in xml
        // TODO: 4. Implement adapter
        students = Model.shared.students

        val listView: ListView = findViewById(R.id.students_list_view)
        listView.adapter = StudentsAdapter(students)

        listView.setOnItemClickListener { parent, view, position, id ->
            Log.d("TAG", "A new row click on cell index: $position")
        }
    }
}