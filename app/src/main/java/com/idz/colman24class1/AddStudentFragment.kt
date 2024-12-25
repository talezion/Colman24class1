package com.idz.colman24class1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddStudentFragment : Fragment() {

    var saveButton: Button? = null
    var cancelButton: Button? = null
    var nameTextField: EditText? = null
    var idTextField: EditText? = null
    var savedTextField: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_student, container, false)
        setupView(view)
        saveButton?.setOnClickListener(::onSaveClicked)
        cancelButton?.setOnClickListener(::onCancelClick)
        return view
    }

    private fun onSaveClicked(view: View) {
        savedTextField?.text = "${nameTextField?.text} ${idTextField?.text} is saved...!!!"
    }

    private fun setupView(view: View?) {
        saveButton = view?.findViewById(R.id.add_student_save_button)
        cancelButton = view?.findViewById(R.id.add_student_cancel_button)
        nameTextField = view?.findViewById(R.id.add_student_name_text_field)
        idTextField = view?.findViewById(R.id.add_student_id_text_field)
        savedTextField = view?.findViewById(R.id.add_student_success_saved_text_view)
    }

    private fun onCancelClick(view: View) {
        // TODO:
    }
}