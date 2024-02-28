package me.ajith.learning.retrofitsample.data.remote

sealed interface UiState<out T> {

    data class Success<T>(val data:T): UiState<T>

    data class Failure(val message:String): UiState<Nothing>

    data object Loading: UiState<Nothing>

}