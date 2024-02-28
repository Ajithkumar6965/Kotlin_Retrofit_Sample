package me.ajith.learning.retrofitsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ajith.learning.retrofitsample.data.remote.UiState
import me.ajith.learning.retrofitsample.data.remote.UserInfo
import me.ajith.learning.retrofitsample.data.remote.ApiHelper

class MainViewModel(private val apiHelper: ApiHelper):ViewModel() {

    private var userData = MutableLiveData<UiState<List<UserInfo>>>()

    fun fetchUserApi(){
        viewModelScope.launch {
            userData.postValue(UiState.Loading)
            try {
                val userPosts = apiHelper.getPosts()
                userData.postValue(UiState.Success(userPosts))
            }catch (exception:Exception){
                exception.localizedMessage?.let {
                    userData.postValue(UiState.Failure(it))
                }
            }

        }
    }

    fun getUserData():LiveData<UiState<List<UserInfo>>>{
        return userData
    }
}