package com.shinaz.base.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinaz.newapp.model.Post
import com.shinaz.newapp.repository.MainRepository
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.model.profile.ProfileResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class
MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val postStateFlow: MutableStateFlow<ApiState<
            List<Post>>> = MutableStateFlow(ApiState.Empty)

    val _postStateFlow: StateFlow<ApiState<
            List<Post>>> = postStateFlow

    private val profileStateFlow: MutableStateFlow<ApiState<ProfileResponse>> = MutableStateFlow(ApiState.Empty)

    val _profileStateFlow: StateFlow<ApiState<
            ProfileResponse>> = profileStateFlow

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepository.getPost()
            .catch { e ->
                postStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                postStateFlow.value = ApiState.Success(data)
            }
    }


}