package com.idz.colman24class1

import android.view.Display.Mode
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.idz.colman24class1.model.Model
import com.idz.colman24class1.model.Student

class StudentsListViewModel: ViewModel() {

    var students: LiveData<List<Student>> = Model.shared.students

    fun refreshAllStudents() {
        Model.shared.refreshAllStudents()
    }
}