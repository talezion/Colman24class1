package com.idz.colman24class1.model

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.idz.colman24class1.base.EmptyCallback
import com.idz.colman24class1.base.StudentsCallback
import com.idz.colman24class1.model.dao.AppLocalDb
import com.idz.colman24class1.model.dao.AppLocalDbRepository
import java.util.concurrent.Executors

class Model private constructor() {

    private val database: AppLocalDbRepository = AppLocalDb.database
    private val executer = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        executer.execute {
            val students = database.studentDao().getAllStudent()

            Thread.sleep(4000)

            mainHandler.post {
                callback(students)
            }
        }
    }

    fun add(student: Student, callback: EmptyCallback) {
        executer.execute {
            database.studentDao().insertAll(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {
        executer.execute {
            database.studentDao().delete(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }
}