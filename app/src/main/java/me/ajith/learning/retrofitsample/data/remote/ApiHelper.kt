package me.ajith.learning.retrofitsample.data.remote

interface ApiHelper {
    suspend fun getPosts():List<UserInfo>
}