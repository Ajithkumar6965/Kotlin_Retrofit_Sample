package me.ajith.learning.retrofitsample.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.ajith.learning.retrofitsample.data.remote.ApiHelper
import me.ajith.learning.retrofitsample.viewmodel.MainViewModel

class ViewModelFactory(val apiHelper: ApiHelper) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }
}