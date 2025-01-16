package com.idz.colman24class1.model

import android.graphics.Bitmap
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.idz.colman24class1.base.EmptyCallback
import com.idz.colman24class1.base.StudentsCallback
import com.idz.colman24class1.model.dao.AppLocalDb
import com.idz.colman24class1.model.dao.AppLocalDbRepository
import java.util.concurrent.Executors


/*
1. Create Firebase model ✅
2. Set and Get ✅
3. Integrate Firestore ✅
4. Integrate Student
 */

class Model private constructor() {

    enum class Storage {
        FIREBASE,
        CLOUDINARY
    }

    private val firebaseModel = FirebaseModel()
    private val cloudinaryModel = CloudinaryModel()
    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        firebaseModel.getAllStudents(callback)
    }

    fun add(student: Student, image: Bitmap?, storage: Storage, callback: EmptyCallback) {
        firebaseModel.add(student) {
            image?.let {
                uploadTo(
                    storage,
                    image = image,
                    name = student.id,
                    callback = { uri ->
                        if (!uri.isNullOrBlank()) {
                            val st = student.copy(avatarUrl = uri)
                            firebaseModel.add(st, callback)
                        } else {
                            callback()
                        }
                    },
                )
            } ?: callback()
        }
    }

    private fun uploadTo(storage: Storage, image: Bitmap, name: String, callback: (String?) -> Unit) {
        when (storage) {
            Storage.FIREBASE -> {
                uploadImageToFirebase(image, name, callback)
            }
            Storage.CLOUDINARY -> {
                uploadImageToCloudinary(
                    bitmap = image,
                    name = name,
                    onSuccess = callback,
                    onError = { callback(null) }
                )
            }
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {
        firebaseModel.delete(student, callback)
    }

    private fun uploadImageToFirebase(
        image: Bitmap,
        name: String,
        callback: (String?) -> Unit
    ) {
        firebaseModel.uploadImage(image, name, callback)
    }

    private fun uploadImageToCloudinary(
        bitmap: Bitmap,
        name: String,
        onSuccess: (String?) -> Unit,
        onError: (String?) -> Unit
    ) {
        cloudinaryModel.uploadImage(
            bitmap = bitmap,
            name = name,
            onSuccess = onSuccess,
            onError = onError
        )
    }
}