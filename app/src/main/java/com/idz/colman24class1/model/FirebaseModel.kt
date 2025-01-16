package com.idz.colman24class1.model

import android.graphics.Bitmap
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.idz.colman24class1.base.Constants
import com.idz.colman24class1.base.EmptyCallback
import com.idz.colman24class1.base.StudentsCallback
import java.io.ByteArrayOutputStream

class FirebaseModel {

    private val database = Firebase.firestore
    private val storage = Firebase.storage

    init {

        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings {  })
        }
        database.firestoreSettings = settings
    }

    fun getAllStudents(callback: StudentsCallback) {
        database.collection(Constants.Collections.STUDENTS).get().addOnCompleteListener {
            when (it.isSuccessful) {
                true -> {
                    val students: MutableList<Student> = mutableListOf()
                    for (json in it.result) {
                        students.add(Student.fromJSON(json.data))
                    }
                    callback(students)
                }
                false -> callback(listOf())
            }
        }
    }

    fun add(student: Student, callback: EmptyCallback) {
        database.collection(Constants.Collections.STUDENTS).document(student.id).set(student.json)
            .addOnCompleteListener {
                callback()
            }
    }

    fun delete(student: Student, callback: EmptyCallback) {

    }

    fun uploadImage(image: Bitmap, name: String, callback: (String?) -> Unit) {
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/$name.jpg")
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            callback(null)
        }.addOnSuccessListener { taskSnapshot ->
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                callback(uri.toString())
            }
        }
    }
}