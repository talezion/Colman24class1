package com.idz.colman24class1

import android.os.Bundle
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.idz.colman24class1.model.Model
import com.idz.colman24class1.model.Student

class AddStudentFragment : Fragment() {

    var saveButton: Button? = null
    var cancelButton: Button? = null
    var nameTextField: EditText? = null
    var idTextField: EditText? = null
    var savedTextField: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
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

        val student = Student(
            id = idTextField?.text?.toString() ?: "",
            name = nameTextField?.text?.toString() ?: "",
            avatarUrl = "",
            isChecked = false
        )

        Model.shared.add(student) {
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun setupView(view: View?) {
        saveButton = view?.findViewById(R.id.add_student_save_button)
        cancelButton = view?.findViewById(R.id.add_student_cancel_button)
        nameTextField = view?.findViewById(R.id.add_student_name_text_field)
        idTextField = view?.findViewById(R.id.add_student_id_text_field)
        savedTextField = view?.findViewById(R.id.add_student_success_saved_text_view)
    }

    private fun onCancelClick(view: View) {
        Navigation.findNavController(view).popBackStack()
    }
}