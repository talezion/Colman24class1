package com.idz.colman24class1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.idz.colman24class1.R
import com.idz.colman24class1.model.Student

class StudentsAdapter(private val students: MutableList<Student>?): BaseAdapter() {

        override fun getCount(): Int = students?.size ?: 0

        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long  = 0

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val inflator = LayoutInflater.from(parent?.context)
//            val view = convertView ?: inflator.inflate(R.layout.student_list_row, parent, false)

            val view = convertView ?: inflator.inflate(R.layout.student_list_row, parent, false).apply {
                findViewById<CheckBox>(R.id.student_row_check_box).apply {
                    setOnClickListener {
                        (tag as? Int)?.let { tag ->
                            val student = students?.get(tag)
                            student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                        }
                    }
                }
            }

//            var view = convertView
//            if (convertView == null) {
//                view = inflator.inflate(R.layout.student_list_row, parent, false)
//                val studentCheckBox: CheckBox? = view?.findViewById(R.id.student_row_check_box)
//
//                studentCheckBox?.apply {
//                    setOnClickListener {
//                        (tag as? Int)?.let { tag ->
//                            val student = students?.get(tag)
//                            student?.isChecked = (it as? CheckBox)?.isChecked ?: false
//                        }
//                    }
//                }
//            }


            val student = students?.get(position)

            val nameTextView: TextView? = view?.findViewById(R.id.student_row_name_text_view)
            val idTextView: TextView? = view?.findViewById(R.id.student_row_id_text_view)
            val studentCheckBox: CheckBox? = view?.findViewById(R.id.student_row_check_box)

            nameTextView?.text = student?.name
            idTextView?.text = student?.id

            studentCheckBox?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }

            return view!!
        }
    }