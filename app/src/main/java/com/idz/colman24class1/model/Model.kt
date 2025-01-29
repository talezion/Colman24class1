package com.idz.colman24class1.model

import android.graphics.Bitmap
import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.idz.colman24class1.base.EmptyCallback
import com.idz.colman24class1.base.StudentsCallback
import com.idz.colman24class1.model.dao.AppLocalDb
import com.idz.colman24class1.model.dao.AppLocalDbRepository
import com.idz.colman24class1.model.networking.MoviesClient
import java.util.concurrent.Executors

class Model private constructor() {

    enum class LoadingState {
        LOADING,
        LOADED
    }

    enum class Storage {
        FIREBASE,
        CLOUDINARY
    }

    private val database: AppLocalDbRepository = AppLocalDb.database
    private var executor = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    val students: LiveData<List<Student>> = database.studentDao().getAllStudent()
    val loadingState: MutableLiveData<LoadingState> = MutableLiveData<LoadingState>()
    val movies: MutableLiveData<Movies> = MutableLiveData()

    private val firebaseModel = FirebaseModel()
    private val cloudinaryModel = CloudinaryModel()
    companion object {
        val shared = Model()
    }

    fun refreshAllStudents() {
        loadingState.postValue(LoadingState.LOADING)
        val lastUpdated: Long = Student.lastUpdated
        firebaseModel.getAllStudents(lastUpdated) { students ->
            executor.execute {
                var currentTime = lastUpdated
                for (student in students) {
                    database.studentDao().insertAll(student)
                    student.lastUpdated?.let {
                        if (currentTime < it) {
                            currentTime = it
                        }
                    }
                }

                Student.lastUpdated = currentTime
                loadingState.postValue(LoadingState.LOADED)
            }
        }
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

    fun getPopularMovies() {
        executor.execute {
            try {
                val request = MoviesClient.moviesApiClient.getPopularMovies(page = 1)
                val response = request.execute()

                if (response.isSuccessful) {
                    val movies = response.body()
                    Log.e("TAG", "Fetched movies!.. with total number of movies ${movies?.results?.size ?: 0}")
                    this.movies.postValue(movies)
                } else {
                    Log.e("TAG", "Failed to fetch movies!")
                }
            } catch (e: Exception) {
                Log.e("TAG", "Failed to fetch movies! with excpetio ${e}")
            }
        }
    }

}