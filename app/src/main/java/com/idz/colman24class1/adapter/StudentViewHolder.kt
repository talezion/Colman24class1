package com.idz.colman24class1.adapter

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class1.OnItemClickListener
import com.idz.colman24class1.R
import com.idz.colman24class1.model.Student

class StudentViewHolder(
    itemView: View,
    listener: OnItemClickListener?
    ): RecyclerView.ViewHolder(itemView) {

        private var nameTextView: TextView? = null
        private var idTextView: TextView? = null
        private var studentCheckBox: CheckBox? = null
        private var student: Student? = null

        init {
            nameTextView = itemView.findViewById(R.id.student_row_name_text_view)
            idTextView = itemView.findViewById(R.id.student_row_id_text_view)
            studentCheckBox = itemView.findViewById(R.id.student_row_check_box)

            studentCheckBox?.apply {
                    setOnClickListener {
                        (tag as? Int)?.let { tag ->
                            student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                        }
                    }
                }

            itemView.setOnClickListener {
                Log.d("TAG", "On click listener on position $adapterPosition")
//                listener?.onItemClick(adapterPosition)
                listener?.onItemClick(student)
            }
        }

        fun bind(student: Student?, position: Int) {
            this.student = student
            nameTextView?.text = student?.name
            idTextView?.text = student?.id

            studentCheckBox?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }
        }
    }