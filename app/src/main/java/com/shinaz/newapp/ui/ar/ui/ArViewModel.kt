package com.shinaz.newapp.ui.ar.ui

import android.content.Context
import androidx.lifecycle.*
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.model.ar.UserData
import com.shinaz.newapp.model.profile.ProfileResponse
import com.shinaz.newapp.room.UserRepository
import com.shinaz.newapp.ui.auth.data.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArViewModel
@Inject
constructor(private val repository: UserRepository) : ViewModel() {
//    private var repository: UserRepository = UserRepository(context)

    // TODO: Implement the ViewModel
   // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
//    val allWords: LiveData<List<UserData>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: UserData) = viewModelScope.launch {
        repository.insert(word)
    }

    private val userFlow: MutableStateFlow<ApiState<List<UserData>?>> = MutableStateFlow(
        ApiState.Empty)

    val _userFlow: StateFlow<ApiState<
            List<UserData>?>> = userFlow

    fun getProfile() = viewModelScope.launch {
        userFlow.value = ApiState.Loading
        repository.users()
            .catch { e ->
                userFlow.value = ApiState.Failure(e)
            }.collect { data ->
                userFlow.value = ApiState.Success(data)
            }
    }
}