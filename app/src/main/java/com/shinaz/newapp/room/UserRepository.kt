package com.shinaz.newapp.room

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread
import com.shinaz.newapp.model.ar.UserData
import com.shinaz.newapp.model.profile.ProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class UserRepository
@Inject
constructor(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
//    val allWords: Flow<List<UserData>> = userDao.getAllUserData()

    fun users(): Flow<List<UserData>?> = flow {
        emit(userDao.getAllUserData())
    }.flowOn(Dispatchers.IO)



    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: UserData) {
        userDao.insert(word)
    }
}