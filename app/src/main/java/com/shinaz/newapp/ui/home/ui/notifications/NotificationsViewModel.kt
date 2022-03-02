package com.shinaz.newapp.ui.home.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shinaz.newapp.repository.MainRepository
import javax.inject.Inject

class NotificationsViewModel
@Inject
constructor(private val mainRepository: MainRepository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}