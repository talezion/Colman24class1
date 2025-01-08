package com.idz.colman24class1.base

import com.idz.colman24class1.model.Student

typealias StudentsCallback = (List<Student>) -> Unit
typealias EmptyCallback = () -> Unit

object Constants {

    object Collections {
        const val STUDENTS = "students"
    }
}