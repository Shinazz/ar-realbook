package com.shinaz.newapp.ui.home.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.model.profile.ProfileResponse
import com.shinaz.newapp.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
@Inject
constructor(private val mainRepository: MainRepository): ViewModel() {

    private val profileStateFlow: MutableStateFlow<ApiState<ProfileResponse>> = MutableStateFlow(ApiState.Empty)

    val _profileStateFlow: StateFlow<ApiState<
            ProfileResponse>> = profileStateFlow
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    fun getProfile() = viewModelScope.launch {
        profileStateFlow.value = ApiState.Loading
        mainRepository.profile()
                .catch { e ->
                    profileStateFlow.value = ApiState.Failure(e)
                }.collect { data ->
                    profileStateFlow.value = ApiState.Success(data)
                }
    }
}