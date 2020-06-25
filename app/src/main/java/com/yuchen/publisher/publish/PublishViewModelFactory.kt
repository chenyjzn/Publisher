package com.yuchen.publisher.publish

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PublishViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PublishViewModel::class.java)) {
            return PublishViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}