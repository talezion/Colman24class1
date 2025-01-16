package com.idz.colman24class1.adapter

import android.util.Log
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class1.OnItemClickListener
import com.idz.colman24class1.R
import com.idz.colman24class1.databinding.StudentListRowBinding
import com.idz.colman24class1.model.Student
import com.squareup.picasso.Picasso

class StudentViewHolder(
    private val binding: StudentListRowBinding,
    listener: OnItemClickListener?
    ): RecyclerView.ViewHolder(binding.root) {

        private var student: Student? = null

        init {
            binding.checkBox.apply {
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
            binding.nameTextView.text = student?.name
            binding.idTextView.text = student?.id

            binding.checkBox.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }

            student?.avatarUrl?.let {
                if (it.isNotBlank()) {
                    Picasso.get()
                        .load(it)
                        .placeholder(R.drawable.avatar)
                        .into(binding.imageView)
                }

            }
        }
    }